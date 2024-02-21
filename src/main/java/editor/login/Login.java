package editor.login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public Login() {
        super("Login");

        // Erstellen von GUI-Komponenten
        JLabel usernameLabel = new JLabel("Benutzername:");
        JLabel passwordLabel = new JLabel("Passwort:");

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                // Hier kannst du die Benutzername- und Passwortprüfung implementieren
                if (!isValidLogin(username, password)) {
                    JOptionPane.showMessageDialog(Login.this, "Ungültige Anmeldeinformationen. Versuche es erneut.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(Login.this, "Login erfolgreich!");
            }
        });

        // Anordnen der GUI-Komponenten im Layout
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);

        // Einstellungen für das Fenster
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null); // Zentrieren des Fensters auf dem Bildschirm
        setVisible(true);
    }

    private boolean isValidLogin(String username, String password) {
        // Hier könntest du die Benutzername- und Passwortprüfung implementieren
        // Zum Beispiel: return username.equals("admin") && password.equals("password");
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login());
    }
}