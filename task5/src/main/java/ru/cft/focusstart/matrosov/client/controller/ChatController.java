package ru.cft.focusstart.matrosov.client.controller;

import ru.cft.focusstart.matrosov.client.model.MessageManager;
import ru.cft.focusstart.matrosov.client.observer.ClientObserver;
import ru.cft.focusstart.matrosov.client.observer.InfoObserver;
import ru.cft.focusstart.matrosov.client.observer.MessageObserver;
import ru.cft.focusstart.matrosov.common.ClientMessage;
import ru.cft.focusstart.matrosov.common.Message;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class ChatController extends JFrame implements MessageObserver, InfoObserver, ClientObserver {

    private JButton confirmButton;
    private JTextField messageTextField;
    private JTextArea chatArea;
    private JTextArea usersArea;


    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public ChatController() {

        MessageManager manager = MessageManager.getInstance();
        manager.addClientObserver(this);
        manager.addMessageObserver(this);
        manager.addInfoObserver(this);

        setTitle("Чат");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridBagLayout());

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridBagLayout());

        messageTextField = new JTextField();
        messageTextField.requestFocusInWindow();

        confirmButton = new JButton("Отправить");
        confirmButton.addActionListener(event -> {
            String text = messageTextField.getText().trim();
            messageTextField.setText("");
            MessageManager.getInstance().sendMessage(text);
        });

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setMargin(new Insets(4,4,4,4));
        DefaultCaret caret = (DefaultCaret) chatArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        chatArea.setFont(new Font("Serif", Font.PLAIN, 14));
        chatArea.setLineWrap(true);

        usersArea = new JTextArea();
        usersArea.setMargin(new Insets(4,4,4,4));
        usersArea.setEditable(false);
        usersArea.setFont(new Font("Serif", Font.BOLD, 13));
        usersArea.setForeground(Color.BLUE);
        usersArea.setLineWrap(true);

        GridBagConstraints bottomLeft = new GridBagConstraints();
        bottomLeft.insets = new Insets(6, 6, 0, 0);
        bottomLeft.anchor = GridBagConstraints.LINE_START;
        bottomLeft.fill = GridBagConstraints.HORIZONTAL;
        bottomLeft.weightx = 512.0;
        bottomLeft.weighty = 1.0;

        GridBagConstraints bottomRight = new GridBagConstraints();
        bottomRight.insets = new Insets(8, 10, 0, 0);
        bottomRight.anchor = GridBagConstraints.LINE_END;
        bottomRight.fill = GridBagConstraints.NONE;
        bottomRight.weightx = 1.0;
        bottomRight.weighty = 1.0;

        GridBagConstraints topLeft = new GridBagConstraints();
        topLeft.insets = new Insets(8, 8, 0, 4);
        topLeft.anchor = GridBagConstraints.LINE_START;
        topLeft.fill = GridBagConstraints.BOTH;
        topLeft.weightx = 3.5;
        topLeft.weighty = 1.0;

        GridBagConstraints topRight = new GridBagConstraints();
        topRight.insets = new Insets(8, 4, 0, 8);
        topRight.anchor = GridBagConstraints.LINE_END;
        topRight.fill = GridBagConstraints.BOTH;
        topRight.weightx = 1.0;
        topRight.weighty = 1.0;

        southPanel.add(messageTextField, bottomLeft);
        southPanel.add(confirmButton, topRight);

        northPanel.add(new JScrollPane(chatArea), topLeft);
        northPanel.add(new JScrollPane(usersArea), topRight);

        mainPanel.add(BorderLayout.CENTER, northPanel);
        mainPanel.add(BorderLayout.SOUTH, southPanel);


        add(mainPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(470, 300);
        setLocationRelativeTo(null);
        setVisible(false);
    }

    @Override
    public void onNewClient(ClientMessage client) {
        usersArea.append("\u25CF " + client.getUserName() + "\n");
    }

    @Override
    public void onNewClientList(List<ClientMessage> clients) {
        usersArea.setText("");
        for (ClientMessage c: clients) {
            usersArea.append("\u25CF " + c.getUserName() + "\n");
        }
    }

    @Override
    public void onInfo(String info) {
        chatArea.append(info + "\n");
    }

    @Override
    public void onNewMessage(Message message) {
        chatArea.append("[" + dateFormat.format(message.getDate()) + "] "
                + message.getFrom() + " :  " + message.getText()
                + "\n");
    }

    @Override
    public void onNewMessages(List<Message> messages) {
        chatArea.setText("");
        for (Message message: messages) {
            chatArea.append("[" + dateFormat.format(message.getDate()) + "] "
                    + message.getFrom() + " :  " + message.getText()
                    + "\n");
        }
    }
}
