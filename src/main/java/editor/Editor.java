package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

            textArea = new JTextArea(28, 150);
            textArea.setFont(new Font("monospaced", Font.PLAIN, 16));
            textArea.setTabSize(4);
            textArea.setBackground(new Color(0x34384c));
            textArea.setForeground(new Color(0xf0f0f0));
            textArea.setCaretColor(new Color(0xf0f0f0));

            scrollPane = new JScrollPane(textArea);
            textArea.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (c == '"' || c == '(' || c == '{' || c == '[' || c == '\'') {
                        e.consume(); // consume the original event
                        int pos = textArea.getCaretPosition();
                        String insert = c + "" + getClosingCharacter(c);
                        textArea.insert(insert, pos);
                        textArea.setCaretPosition(pos + 1); // set the caret between the characters
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
            });

            outputArea = new JTextArea(10, 60);
            outputArea.setEditable(false);
            outputArea.setFont(new Font("monospaced", Font.PLAIN, 16));
            outputScrollPane = new JScrollPane(outputArea);

            runButton = new JButton("Run Code");
            runButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CodeRunner code = new CodeRunner(textArea.getText());
                    outputArea.setText(code.getOutput());
                }
            });

            // Create a panel for the buttons
            buttonPanel = new JPanel();
//            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
            buttonPanel.setBackground(new Color(0x34384c));
            buttonPanel.setPreferredSize(new Dimension(250, 50));

            // Add some buttons to the panel
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
        JButton button = new JButton("script" + i + ".malu");
        button.setFont(new Font("monospaced", Font.PLAIN, 12));
        button.setPreferredSize(new Dimension(250, 20));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBackground(new Color(0x34384c));
        button.setForeground(new Color(0xf0f0f0));
        button.setBorderPainted(false);
        button.setFocusPainted(true);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0x4f5b93));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0x34384c));
            }
        });
        return button;
    }
}