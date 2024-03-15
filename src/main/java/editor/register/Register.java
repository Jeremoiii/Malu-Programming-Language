package editor.register;

import client.Main;
import shared.User;
import utils.JSON.JSON;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static utils.SHA1.sha1Hash;

public class Register extends JFrame implements ActionListener {
    private Main client;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton createButton;

    public Register(Main client) {
        this.client = client;
        this.client.setRegisterFrame(Register.this);

        setTitle("Registrieren");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordField = new JPasswordField();

        createButton = new JButton("Create Account");
        createButton.addActionListener(Register.this);

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(confirmPasswordLabel);
        panel.add(confirmPasswordField);
        panel.add(new JLabel());
        panel.add(createButton);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton) {
            String username = usernameField.getText();
            char[] cPassword = passwordField.getPassword();
            char[] ConfirmPassword = confirmPasswordField.getPassword();

            if (!passwordMatch(cPassword, ConfirmPassword)) {
                JOptionPane.showMessageDialog(Register.this, "Passwörter stimmen nicht überein!", "Fehler!", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(Register.this, "Account wurde erfolgreich registriert!", "Erfolg!", JOptionPane.INFORMATION_MESSAGE);
            usernameField.setText("");
            passwordField.setText("");
            confirmPasswordField.setText("");
            setVisible(false);

            client.sendNetMessage("register",
                JSON.stringify(
                    new User(
                        username,
                        sha1Hash(
                            new String(cPassword)
                        )
                    )
                )
            );
        }
    }

    private boolean passwordMatch(char[] password1, char[] password2) {
        if (password1.length != password2.length) {
            return false;
        }

        for (int i = 0; i < password1.length; i++) {
            if (password1[i] != password2[i]) {
                return false;
            }
        }
        return true;
    }
}
