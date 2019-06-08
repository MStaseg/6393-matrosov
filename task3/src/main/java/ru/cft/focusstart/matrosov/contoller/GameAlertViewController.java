package ru.cft.focusstart.matrosov.contoller;

import ru.cft.focusstart.matrosov.model.stat.StatisticManager;

import javax.swing.*;

/**
 * Controller that represents the modal window for asking user to enter his name
 */
class GameAlertViewController extends JDialog {

    private JTextField textField;

    GameAlertViewController() {
        getContentPane().setLayout(null);
        setSize(250, 132);
        setResizable(false);
        setTitle("Введите ваше имя");

        addTextField();
        addConfirmButton();
        addCancelButton();
    }

    private void addTextField() {
        textField = new JTextField();
        textField.setBounds(16,16, 218,30);
        add(textField);
    }

    private void addConfirmButton() {
        JButton confirmButton = new JButton();
        confirmButton.setText("Хорошо");
        confirmButton.addActionListener(e -> {
            System.out.println(textField.getText().trim());
            StatisticManager.getInstance().setUserName(textField.getText().trim());
            dispose();
        });
        confirmButton.setBounds(16,62, 101,30);
        add(confirmButton);
    }

    private void addCancelButton() {
        JButton cancelButton = new JButton();
        cancelButton.setText("Отмена");
        cancelButton.addActionListener(e -> {
            StatisticManager.getInstance().setUserName(null);
            dispose();
        });
        cancelButton.setBounds(133,62, 101,30);
        add(cancelButton);
    }
}
