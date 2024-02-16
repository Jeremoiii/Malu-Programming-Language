package editor;

import editor.listeners.ActionEventListener;
import editor.listeners.KeyEventListener;
import editor.listeners.MouseEventListener;

import javax.swing.*;
import java.awt.*;

public class Editor {
    private JFrame frame;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JTextArea outputArea;
    private JScrollPane outputScrollPane;
    private JButton runButton;
    private JPanel buttonPanel;

    public Editor() {
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

            runButton = new JButton("Run Code");
            runButton.addActionListener(new ActionEventListener(textArea, outputArea));

            buttonPanel = new JPanel();
            buttonPanel.setBackground(new Color(0x34384c));
            buttonPanel.setPreferredSize(new Dimension(250, 50));

            for (int i = 1; i <= 5; i++) {
                JButton button = createButton(i);
                buttonPanel.add(button);
            }

            frame.add(buttonPanel, BorderLayout.WEST);
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.add(outputScrollPane, BorderLayout.SOUTH);
            frame.add(runButton, BorderLayout.NORTH);
            frame.pack();
            frame.setVisible(true);
        });
    }

    private static JButton createButton(int i) {
        JButton button = new JButton("(test) script" + i + ".malu");
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
}