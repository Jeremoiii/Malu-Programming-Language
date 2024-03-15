package editor.listeners;

import client.Main;
import editor.CodeRunner;
import editor.Editor;
import editor.CreateFileModal;
import shared.NetProjectFile;
import shared.ProjectFile;
import shared.Projects;
import utils.JSON.JSON;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionEventListener implements ActionListener {
    private final JTextArea textArea;
    private final JTextArea outputArea;
    private final String action;
    private final Object[] args;
    private JButton lastClickedButton;

    public ActionEventListener(String action, JTextArea textArea, JTextArea outputArea, Object... args) {
        this.textArea = textArea;
        this.outputArea = outputArea;
        this.action = action;
        this.args = args;
        this.lastClickedButton = null;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() instanceof JButton) {
            lastClickedButton = (JButton) event.getSource();
        }

        switch (action) {
            case "run":
                runCode();
                break;
            case "save":
                saveFile((Editor) this.args[0]);
                break;
            case "new":
                newFile((Editor) this.args[0], (Projects) this.args[1]);
                break;
            case "open":
                openFile((Editor) this.args[0], (Projects) this.args[1]);
                break;
            default:
                System.out.println("Invalid action");
                break;
        }
    }

    public void runCode() {
        CodeRunner code = new CodeRunner(textArea.getText());
        outputArea.setText(code.getOutput());
    }

    public void saveFile(Editor editor) {
        ProjectFile file = editor.getFile();

        System.out.println(file);

        if (file == null) {
            JOptionPane.showMessageDialog(null, "Wähle bitte eine Datei aus!", "Fehler", JOptionPane.ERROR_MESSAGE);
            return;
        }

        file.setContent(textArea.getText());

        editor.getClient().sendNetMessage("saveFile",
            JSON.stringify(
                new NetProjectFile(
                    file.getName(),
                    file.getContent(),
                    file.getOwner()
                )
            )
        );
    }

    public void newFile(Editor editor, Projects projects) {
        CreateFileModal modal = new CreateFileModal(projects, editor);
        modal.setVisible(true);
    }

    public void openFile(Editor editor, Projects project) {
        if (lastClickedButton.getText().endsWith(".malu")) {

            ProjectFile file = null;
            project.getProjects().toFirst();
            while (project.getProjects().hasAccess()) {
                String fileName = project.getProjects().getContent().getName();
                String buttonName = lastClickedButton.getText();

                System.out.println("fileName: " + fileName);
                System.out.println("buttonName: " + buttonName);

                if (fileName.equals(buttonName)) {
                    file = project.getProjects().getContent();
                    break;
                }
                project.getProjects().next();
            }

            if (file == null) {
                JOptionPane.showMessageDialog(null, "Datei existiert nicht.", "Fehler", JOptionPane.ERROR_MESSAGE);
                return;
            }

            textArea.setText(file.getContent());
            outputArea.setText("");

            editor.getRunButton().setEnabled(true);
            editor.getSaveButton().setEnabled(true);


            editor.setFile(file);
        }
    }
}
