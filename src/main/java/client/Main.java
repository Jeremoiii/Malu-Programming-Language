package client;

import editor.login.Login;
import generic.Network.Client;
import generic.DataStructures.List;
import net.NetEvent;
import shared.CallbackHelper.CallbackState;
import shared.User;

import javax.swing.*;

import static net.NetEventParser.parseString;

public class Main extends Client {
    private JFrame loginFrame;
    private JFrame registerFrame;
    private List<CallbackState> callbacks;
    public User user;

    public Main() {
        // Verbindung mit dem Server herstellen
        super("127.0.0.1", 8080);
        System.out.println("Stelle Verbindung mit dem Server her...");

        // Callback-Liste initialisieren
        this.callbacks = new List<>();

        // Login-Fenster anzeigen
        SwingUtilities.invokeLater(() -> {
            new Login(this);
        });
    }

    @Override
    public void processMessage(String netEventMessage) {
        String input = netEventMessage;
        NetEvent event = parseString(input);

        switch (event.getEventName()) {
            case "net:error":
                JOptionPane.showMessageDialog(this.registerFrame, event.getContent(), "Fehler!", JOptionPane.ERROR_MESSAGE);
                this.registerFrame.setVisible(true);
                break;
            default:
                break;
        }
    }

    public void sendNetMessage(String event, String message) {
        this.send("[__ctx:net:" + event + "]" + message);
    }

    public void setLoginFrame(JFrame loginFrame) {
        this.loginFrame = loginFrame;
    }

    public void setRegisterFrame(JFrame registerFrame) {
        this.registerFrame = registerFrame;
    }

    public List<CallbackState> getCallbacks() {
        return this.callbacks;
    }
}
