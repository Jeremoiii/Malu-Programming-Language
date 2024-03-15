package editor.login;

import client.Main;
import editor.Editor;
import editor.register.Register;
import shared.Callback;
import shared.Projects;
import shared.User;
import utils.JSON.JSON;
import utils.ObjectPrinter;
import utils.SHA1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private Main client;


    public Login(Main client) {
        super("Login");

        this.client = client;
        this.client.setLoginFrame(Login.this);

        JLabel usernameLabel = new JLabel("Benutzername:");
        JLabel passwordLabel = new JLabel("Passwort:");

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Registrieren");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = SHA1.sha1Hash(new String(passwordChars));

                client.user = new User(username, password);

                boolean success = (boolean) Callback.Trigger(client, "login", JSON.stringify(client.user), (netMessage) -> {
                    return netMessage == "true";
                });

                if (!success) {
                    JOptionPane.showMessageDialog(Login.this, "Ungültige Anmeldeinformationen. Versuche es erneut.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    setVisible(true);
                    return;
                }

                JOptionPane.showMessageDialog(Login.this, "Login erfolgreich!");
                setVisible(false);

                Projects files = (Projects) Callback.Trigger(client, "getUserFiles", JSON.stringify(client.user), (netMessage) -> {
                    Projects projects = JSON.parse(netMessage, Projects.class);
                    return projects;
                });

                new Editor(files, client);
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Register(client);
            }
        });

        // Panel für die Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(buttonPanel); // Hinzufügen des Panels mit den Buttons

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private boolean isValidLogin(String username, String password) {
        client.send("[__ctx:net:login]" + " " + username + ":" + password);
        return false;
    }
}
