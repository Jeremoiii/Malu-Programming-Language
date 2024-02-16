package editor.listeners;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

public class MouseEventListener extends MouseAdapter {
    private final JButton button;

    public MouseEventListener(JButton button) {
        this.button = button;
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent evt) {
        button.setBackground(new Color(0x4f5b93));
    }
    @Override
    public void mouseExited(java.awt.event.MouseEvent evt) {
        button.setBackground(new Color(0x34384c));
    }
}
