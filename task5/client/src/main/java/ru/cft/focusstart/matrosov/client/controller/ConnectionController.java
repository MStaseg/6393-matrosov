package ru.cft.focusstart.matrosov.client.controller;

import ru.cft.focusstart.matrosov.client.exception.ConnectionManagerException;
import ru.cft.focusstart.matrosov.client.model.ConnectionManager;
import ru.cft.focusstart.matrosov.client.model.MessageManager;
import ru.cft.focusstart.matrosov.client.observer.ErrorObserver;
import ru.cft.focusstart.matrosov.client.observer.SuccessObserver;

import javax.swing.*;

public class ConnectionController extends JFrame implements SuccessObserver, ErrorObserver {

    private JTextField serverTextField;
    private JTextField portTextField;
    private JTextField userNameTextField;

    private AlertController alert;

    ConnectionController() {
        setupComponents();
        MessageManager.instance.addSuccessObserver(this);
        MessageManager.instance.addErrorObserver(this);
        ConnectionManager.instance.addErrorObserver(this);
    }

    private void setupComponents() {
        setTitle("Подключение к серверу");

        JLabel serverLabel = new JLabel("Сервер");
        JLabel portLabel = new JLabel("Порт");
        JLabel userNameLabel = new JLabel("Ник");

        serverTextField = new JTextField();
        portTextField = new JTextField();
        userNameTextField = new JTextField();

        JButton connectBtn = new JButton("Войти");

        serverLabel.setBounds(16, 16, 70, 30);
        portLabel.setBounds(16, 62, 70, 30);
        userNameLabel.setBounds(16, 108, 70, 30);

        serverTextField.setBounds(102, 16, 200, 30);
        portTextField.setBounds(102, 62, 100, 30);
        userNameTextField.setBounds(102, 108, 200, 30);

        connectBtn.setBounds(16, 154, 100, 30);
        connectBtn.addActionListener((event) -> {
            try {
                int port = Integer.parseInt(portTextField.getText());
                String server = serverTextField.getText();
                String userName = userNameTextField.getText().trim();
                if (userName.equals("")) {
                    onError("Некооректный ник");
                } else {
                    ConnectionManager.instance.connect(server, port, userName);
                }
            } catch (ConnectionManagerException e) {
                onError("Не удалось подключиться");
            } catch (NumberFormatException e) {
                onError("Некорректный порт");
            }
        });

        add(serverLabel);
        add(serverTextField);
        add(portLabel);
        add(portTextField);
        add(userNameLabel);
        add(userNameTextField);
        add(connectBtn);

        setSize(350, 216);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(false);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    @Override
    public void onError(String cause) {
        setVisible(true);
        clearAlert();
        alert = new AlertController(cause);
        alert.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        alert.setVisible(true);
        ControllerManager.chatController.setVisible(false);
    }

    @Override
    public void onSuccess() {
        setVisible(false);
        clearAlert();
        ControllerManager.chatController.setVisible(true);
    }

    private void clearAlert() {
        if (alert != null) {
            alert.dispose();
        }
    }
}
