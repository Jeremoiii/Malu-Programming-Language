package editor;

import shared.Projects;
import utils.StringUtil;

import javax.swing.*;

public class CreateFileModal extends JFrame {
    private JTextField fileName;

    public CreateFileModal(Projects projects, Editor editor) {
        super("Modal");

        JLabel fileNameLabel = new JLabel("Dateiname:");

        fileName = new JTextField(20);

        JButton saveButton = new JButton("Speichern");
        saveButton.addActionListener(event -> {
            String name = fileName.getText();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(CreateFileModal.this, "Dateiname darf nicht leer sein.", "Fehler", JOptionPane.ERROR_MESSAGE);
                return;
            }

            name = StringUtil.convertToCamelCase(name);
            name += ".malu";

            projects.getProjects().toFirst();
            while (projects.getProjects().hasAccess()) {
                if (projects.getProjects().getContent().getName().equals(name)) {
                    JOptionPane.showMessageDialog(CreateFileModal.this, "Datei existiert bereits.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                projects.getProjects().next();
            }

            JOptionPane.showMessageDialog(CreateFileModal.this, "Datei gespeichert!");
            setVisible(false);

            editor.addProject(name, "", true);
        });

        JPanel panel = new JPanel();
        panel.add(fileNameLabel);
        panel.add(fileName);
        panel.add(saveButton);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
