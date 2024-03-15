package shared;

import utils.ObjectPrinter;

import javax.swing.*;

public class ProjectFile {
    String name;
    String content;
    User owner;
    JButton button;

    public ProjectFile(JButton button, String name, String content, User owner) {
        this.name = name;
        this.content = content;
        this.button = button;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public JButton getButton() {
        return button;
    }

}
