package ru.cft.focusstart.matrosov.server.service;

import ru.cft.focusstart.matrosov.common.Message;

import java.util.List;

public interface MessageService {
    Message add(Message message);
    List<Message> getMessages();
}
