package ru.cft.focusstart.matrosov.server.service;

import ru.cft.focusstart.matrosov.common.Message;
import ru.cft.focusstart.matrosov.server.repository.Repositories;

import java.util.List;

public final class DefaultMessageService implements MessageService {

    static final DefaultMessageService instance = new DefaultMessageService();

    private DefaultMessageService() {}

    @Override
    public Message add(Message message) {
        Repositories.getMessageRepository().add(message);
        return message;
    }

    @Override
    public List<Message> getMessages() {
        return Repositories.getMessageRepository().get();
    }
}
