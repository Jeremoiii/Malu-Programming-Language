package editor;

import client.Main;
import editor.listeners.ActionEventListener;
import editor.listeners.KeyEventListener;
import editor.listeners.MouseEventListener;
import shared.NetProjectFile;
import shared.ProjectFile;
import shared.Projects;
import utils.JSON.JSON;
import utils.JSON.JSONStringModifier;
import utils.JSON.MapToJSONConverter;

import javax.swing.*;
import java.awt.*;

public class Editor {
    private final Main client;
    private JFrame frame;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JTextArea outputArea;
    private JScrollPane outputScrollPane;
    private JButton runButton;
    private JButton saveButton;
    private JButton newFile;
    private JPanel buttonPanel;
    private Projects projects;
    private ProjectFile file;

    public Editor(Projects projects, Main client) {
        this.projects = projects;
        this.client = client;

        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Simple Code Editor");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setBackground(new Color(0x34384c));

            textArea = new JTextArea(28, 120);
            textArea.setFont(new Font("monospaced", Font.PLAIN, 16));
            textArea.setTabSize(4);
            textArea.setBackground(new Color(0x34384c));
            textArea.setForeground(new Color(0xf0f0f0));
            textArea.setCaretColor(new Color(0xf0f0f0));

            scrollPane = new JScrollPane(textArea);
            textArea.addKeyListener(new KeyEventListener(textArea));

            outputArea = new JTextArea(10, 60);
            outputArea.setEditable(false);
            outputArea.setFont(new Font("monospaced", Font.PLAIN, 16));
            outputScrollPane = new JScrollPane(outputArea);

            buttonPanel = new JPanel();
            buttonPanel.setBackground(new Color(0x34384c));
            buttonPanel.setPreferredSize(new Dimension(250, 50));

            if (this.projects.getNetListSize() == 0) {
                JButton button = createButton("ungespeichert.malu");
                button.addActionListener(
                    new ActionEventListener(
                        "open",
                        textArea,
                        outputArea,
                        this,
                        projects
                    )
                );
                buttonPanel.add(button);

                file = new ProjectFile(button, "ungespeichert.malu", "", client.user);
                projects.addProject(file);
            }

            textArea.setText("Keine Datei offen, bitte wählen Sie eine Datei aus oder erstellen Sie eine neue Datei.");

            runButton = new JButton("Ausführen");
            runButton.addActionListener(new ActionEventListener("run", textArea, outputArea));

            saveButton = new JButton("Speichern");
            saveButton.addActionListener(new ActionEventListener("save", textArea, outputArea, this));

            runButton.setEnabled(false);
            saveButton.setEnabled(false);

            newFile = new JButton("Neue Datei");
            newFile.addActionListener(new ActionEventListener("new", textArea, outputArea, this, projects));


            frame.add(buttonPanel, BorderLayout.WEST);
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.add(outputScrollPane, BorderLayout.SOUTH);

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            buttonPanel.setBackground(new Color(0x34384c));
            buttonPanel.add(runButton);
            buttonPanel.add(saveButton);
            buttonPanel.add(newFile);
            frame.add(buttonPanel, BorderLayout.NORTH);

            frame.pack();
            frame.setVisible(true);

            projects.getNetProjects().toFirst();
            while (projects.getNetProjects().hasAccess()) {
                String JSONString = new String(String.valueOf(projects.getNetProjects().getContent()));
                JSONString = MapToJSONConverter.convertToJSON(JSONString);
                NetProjectFile netProjectFile = JSON.parse(JSONStringModifier.removeOwnerPart(JSONString), NetProjectFile.class);
                this.addProject(netProjectFile.getName(), netProjectFile.getContent(), false);
                file.setContent(netProjectFile.getContent());

                projects.getNetProjects().next();
            }
        });
    }

    private static JButton createButton(String name) {
        JButton button = new JButton(name);
        button.setFont(new Font("monospaced", Font.PLAIN, 12));
        button.setPreferredSize(new Dimension(250, 20));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBackground(new Color(0x34384c));
        button.setForeground(new Color(0xf0f0f0));
        button.setBorderPainted(false);
        button.setFocusPainted(true);

        button.addMouseListener(new MouseEventListener(button));
        return button;
    }

    public void addProject(String projectName, String content, boolean isNew) {
        JButton button = createButton(projectName);
        button.addActionListener(
            new ActionEventListener(
                "open",
                textArea,
                outputArea,
                this,
                projects
            )
        );

        buttonPanel.add(button);
        frame.revalidate();

        file = new ProjectFile(button, projectName, "", this.client.user);
        projects.addProject(file);

        if (isNew) {
            this.client.sendNetMessage("createFile", JSON.stringify(new NetProjectFile(projectName, content, this.client.user)));
        }
    }

    public void setFile(ProjectFile file) {
        this.file = file;
    }

    public ProjectFile getFile() {
        return this.file;
    }

    public Main getClient() {
        return this.client;
    }

    public JButton getRunButton() {
        return this.runButton;
    }

    public JButton getSaveButton() {
        return this.saveButton;
    }
}