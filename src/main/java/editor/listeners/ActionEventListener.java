package editor.listeners;

import editor.CodeRunner;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionEventListener implements ActionListener {
    private final JTextArea textArea;
    private final JTextArea outputArea;

    public ActionEventListener(JTextArea textArea, JTextArea outputArea) {
        this.textArea = textArea;
        this.outputArea = outputArea;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        CodeRunner code = new CodeRunner(textArea.getText());
        outputArea.setText(code.getOutput());
    }
}
