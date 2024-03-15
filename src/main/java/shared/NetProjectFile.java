package shared;

import utils.ObjectPrinter;

import javax.swing.*;

// Java ist so fucking stupid. Es ist so dumm, dass ich die JFrame Objekte nicht serialisieren kann...
// Deswegen die Net-Klasse ohne die JFrame Objekte..
public class NetProjectFile {
    public String name;
    private String content;
    private User owner;

    public NetProjectFile(String name, String content, User owner) {
        this.name = name;
        this.content = content;
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
}
