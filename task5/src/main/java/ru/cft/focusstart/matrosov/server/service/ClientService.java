package ru.cft.focusstart.matrosov.server.service;

import ru.cft.focusstart.matrosov.common.Message;

import java.net.Socket;
import java.util.List;

public interface ClientService {
    void addClient(Socket socket);
    void sendMessage(Message message);
    void sendMessageList(List<Message> messages);
}
