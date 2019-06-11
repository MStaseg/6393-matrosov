package ru.cft.focusstart.matrosov.contoller;

import ru.cft.focusstart.matrosov.model.stat.StatisticManager;

import javax.swing.*;

/**
 * Controller that represents the modal window for asking user to enter his name
 */
class GameAlertViewController extends JDialog {

    private JTextField textField;
    private String bufferedName;

    GameAlertViewController() {
        getContentPane().setLayout(null);
        setSize(250, 132);
        setResizable(false);
        setTitle("Введите ваше имя");

        addTextField();
        addConfirmButton();
        addCancelButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void addTextField() {
        textField = new JTextField();
        textField.setBounds(16, 16, 218, 30);
        add(textField);
    }

    private void addConfirmButton() {
        JButton confirmButton = new JButton();
        confirmButton.setText("Хорошо");
        confirmButton.addActionListener(e -> {
            bufferedName = textField.getText().trim();
            dispose();
        });
        confirmButton.setBounds(16, 62, 101, 30);
        add(confirmButton);
    }

    private void addCancelButton() {
        JButton cancelButton = new JButton();
        cancelButton.setText("Отмена");
        cancelButton.addActionListener(e -> dispose());
        cancelButton.setBounds(133,62, 101,30);
        add(cancelButton);
    }

    @Override
    public void dispose() {
        StatisticManager.getInstance().setUserName(bufferedName);
        super.dispose();
    }
}
