package ru.cft.focusstart.matrosov.client.view;

import javax.swing.*;
import java.awt.*;

public class MessageView extends JScrollPane {
    private JTextArea textArea;

    public MessageView() {
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setBackground(Color.BLUE);
        add(textArea);
        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    public void setText(String text) {
        textArea.setText(text);
    }

    public void append(String text) {
        textArea.append(text);
    }
}
