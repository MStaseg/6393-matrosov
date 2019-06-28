package ru.cft.focusstart.matrosov.client.controller;

import ru.cft.focusstart.matrosov.client.model.MessageManager;
import ru.cft.focusstart.matrosov.client.observer.ClientObserver;
import ru.cft.focusstart.matrosov.client.observer.InfoObserver;
import ru.cft.focusstart.matrosov.client.observer.MessageObserver;
import ru.cft.focusstart.matrosov.common.ClientMessage;
import ru.cft.focusstart.matrosov.common.Message;
import ru.cft.focusstart.matrosov.server.net.Client;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChatController extends JFrame implements MessageObserver, InfoObserver, ClientObserver {

    private JButton confirmButton;
    private JTextField messageTextField;
    private JTextArea chatArea;
    private JTextArea usersArea;

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

        confirmButton = new JButton("Send Message");
        //confirmButton.addActionListener(new MainScreenController.sendMessageButtonListener());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        //chatArea.setFont(new Font("Serif", Font.PLAIN, 15));
        chatArea.setLineWrap(true);

        usersArea = new JTextArea();
        usersArea.setEditable(false);
        //chatArea.setFont(new Font("Serif", Font.PLAIN, 15));
        usersArea.setLineWrap(true);

        //mainPanel.add(new JScrollPane(chatArea), BorderLayout.CENTER);

        GridBagConstraints bottomLeft = new GridBagConstraints();
        bottomLeft.insets = new Insets(6, 6, 0, 0);
        bottomLeft.anchor = GridBagConstraints.LINE_START;
        bottomLeft.fill = GridBagConstraints.HORIZONTAL;
        bottomLeft.weightx = 512.0D;
        bottomLeft.weighty = 1.0D;

        GridBagConstraints bottomRight = new GridBagConstraints();
        bottomRight.insets = new Insets(0, 10, 0, 0);
        bottomRight.anchor = GridBagConstraints.LINE_END;
        bottomRight.fill = GridBagConstraints.NONE;
        bottomRight.weightx = 1.0D;
        bottomRight.weighty = 1.0D;

        GridBagConstraints topLeft = new GridBagConstraints();
        topLeft.insets = new Insets(8, 10, 0, 8);
        topLeft.anchor = GridBagConstraints.LINE_START;
        topLeft.fill = GridBagConstraints.VERTICAL;
        topLeft.weightx = topLeft.weighty = 10.0;

        GridBagConstraints topRight = new GridBagConstraints();
        topRight.insets = new Insets(8, 10, 0, 8);
        topRight.anchor = GridBagConstraints.LINE_END;
        topRight.fill = GridBagConstraints.VERTICAL;
        topRight.weightx = topRight.weighty = 10.0;

        southPanel.add(messageTextField, bottomLeft);
        southPanel.add(confirmButton, topRight);

        northPanel.setBackground(Color.YELLOW);
        northPanel.add(new JScrollPane(chatArea), topLeft);
        northPanel.add(new JScrollPane(usersArea), topRight);

        mainPanel.add(BorderLayout.CENTER, northPanel);
        mainPanel.add(BorderLayout.SOUTH, southPanel);


        add(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(470, 300);
        setLocationRelativeTo(null);
        setVisible(false);
    }

    @Override
    public void onNewClient(ClientMessage client) {
        usersArea.append(client.getUserName() + "\n");
    }

    @Override
    public void onNewClientList(List<ClientMessage> clients) {
        usersArea.setText("");
        for (ClientMessage c: clients) {
            usersArea.append(c.getUserName() + "\n");
        }
    }

    @Override
    public void onInfo(String info) {
        usersArea.append("<span style=\"font-style: italic;\">" + info + "</span>\n");
    }

    @Override
    public void onNewMessage(Message message) {
        chatArea.append("<" + message.getFrom() + ">:  " + message.getText()
                + "\n");
    }

    @Override
    public void onNewMessages(List<Message> messages) {
        chatArea.setText("");
        for (Message m: messages) {
            chatArea.append("<" + m.getFrom() + ">:  " + m.getText()
                    + "\n");
        }
    }
}
