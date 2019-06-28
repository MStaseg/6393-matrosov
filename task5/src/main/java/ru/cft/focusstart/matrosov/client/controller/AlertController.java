package ru.cft.focusstart.matrosov.client.controller;

import javax.swing.*;

public class AlertController extends JDialog {

    private JLabel textLabel;
    private String text;

    AlertController(String message) {
        text = message;
        getContentPane().setLayout(null);
        setSize(350, 132);
        setResizable(false);
        setTitle("Внимание");

        addTextField();
        addConfirmButton();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        toFront();
    }

    private void addTextField() {
        textLabel = new JLabel();
        textLabel.setBounds(16, 16, 318, 30);
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText(text);
        add(textLabel);
    }

    private void addConfirmButton() {
        JButton confirmButton = new JButton();
        confirmButton.setText("Хорошо");
        confirmButton.addActionListener(e -> {
            dispose();
        });
        confirmButton.setBounds(125, 62, 100, 30);
        add(confirmButton);
    }
}