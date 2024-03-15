package editor.listeners;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyEventListener extends KeyAdapter {
    private final JTextArea textArea;

    public KeyEventListener(JTextArea textArea) {
        this.textArea = textArea;
    }
    @Override
    public void keyTyped(KeyEvent event) {
        char c = event.getKeyChar();
        if (c == '"' || c == '(' || c == '{' || c == '[' || c == '\'') {
            event.consume();
            int pos = textArea.getCaretPosition();
            String insert = c + "" + getClosingCharacter(c);
            textArea.insert(insert, pos);
            textArea.setCaretPosition(pos + 1);
        }

    }

    private char getClosingCharacter(char c) {
        return switch (c) {
            case '(' -> ')';
            case '{' -> '}';
            case '[' -> ']';
            default -> c;
        };
    }
}
