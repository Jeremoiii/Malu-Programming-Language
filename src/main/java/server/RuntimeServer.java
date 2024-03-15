package server;

import generic.Network.Server;
import generic.SQL.QueryResult;
import net.NetEvent;
import shared.*;
import utils.AES;
import utils.JSON.JSON;
import utils.ObjectPrinter;

import static net.NetEventParser.parseString;

public class RuntimeServer extends Server {
    Database db = new Database();

    public RuntimeServer(int pPort) {
        super(pPort);
    }

    @Override
    public void processNewConnection(String pClientIP, int pClientPort) {
        System.out.println("New connection from " + pClientIP + ":" + pClientPort);
    }

    @Override
    public void processMessage(String ip, int port, String netEventMessage) {
        String input = netEventMessage;
        NetEvent event = parseString(input);

        switch (event.getEventName()) {
            case "net:login":
                User loginUser = JSON.parse(event.getContent(), User.class);

                System.out.println(ObjectPrinter.deserializeObjectToString(loginUser));

                QueryResult isValid = db.execute("SELECT * FROM `malu_user` WHERE `user` = '" + loginUser.getUsername() + "' AND `password` = '" + loginUser.getPassword() + "'");

                boolean valid = isValid != null && isValid.getData().length != 0;
                Callback.Resolve(this, ip, port, "login", valid ? "true" : "false");
                break;

            case "net:register":
                User newUser = JSON.parse(event.getContent(), User.class);

                QueryResult exists = db.execute("SELECT * FROM `malu_user` WHERE `user` = '" + newUser.getUsername() + "'");

                if (exists.getData().length != 0) {
                    System.out.println("User already exists");

                    sendNetMessage(ip, port, "error", "Benutzername existiert bereits!");
                    return;
                }

                QueryResult result = db.execute("INSERT INTO `malu_user` (`user`, `password`) VALUES ('" + newUser.getUsername() + "', '" + newUser.getPassword() + "')");
                break;

            case "net:getUserFiles":
                User user = JSON.parse(event.getContent(), User.class);
                QueryResult dbProjects = db.execute("SELECT * FROM `malu_projects` WHERE `owner` = '" + user.getUsername() + "'");

                if (dbProjects.getData().length == 0) {
                    Callback.Resolve(this, ip, port, "getProjects", JSON.stringify(new Projects()));
                    return;
                }

                Projects projects = new Projects();
                for (int i = 0; i < dbProjects.getData().length; i++) {
                    try {
                        NetProjectFile netProjectFile = new NetProjectFile(dbProjects.getData()[i][2], AES.decrypt(dbProjects.getData()[i][3], user.getPassword()), user);
                        projects.getNetProjects().append(netProjectFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                Callback.Resolve(this, ip, port, "getProjects", JSON.stringify(projects));
                break;

            case "net:createFile":
                NetProjectFile newFile = JSON.parse(event.getContent(), NetProjectFile.class);

                QueryResult fileExists = db.execute("SELECT * FROM `malu_projects` WHERE `owner` = '" + newFile.getName() + "'");
                if (fileExists.getData().length != 0) {
                    sendNetMessage(ip, port, "error", "Projekt existiert bereits!");
                    return;
                }

                try {
                    String encryptedContent = AES.encrypt(newFile.getContent(), newFile.getOwner().getPassword());
                    QueryResult createFile = db.execute("INSERT INTO `malu_projects` (`owner`, `name`, `src`) VALUES ('" + newFile.getOwner().getUsername() + "', '" + newFile.getName() + "', '" + encryptedContent + "')");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            case "net:saveFile":
                NetProjectFile saveFile = JSON.parse(event.getContent(), NetProjectFile.class);

                try {
                    String encryptedContent = AES.encrypt(saveFile.getContent(), saveFile.getOwner().getPassword());
                    QueryResult updateFile = db.execute("UPDATE `malu_projects` SET `src` = '" + encryptedContent + "' WHERE `owner` = '" + saveFile.getOwner().getUsername() + "' AND `name` = '" + saveFile.getName() + "'");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            default:
                System.out.println(event.getContent());
        }
    }

    public void sendNetMessage(String ip, int port, String event, String message) {
        this.send(ip, port,"[__ctx:net:" + event + "]" + message);
    }
}
