package ru.cft.focusstart.matrosov.server.repository;

import ru.cft.focusstart.matrosov.common.Message;

import java.util.ArrayList;
import java.util.List;

public class MemoryMessageRepository implements MessageRepository {

    static final MemoryMessageRepository instance = new MemoryMessageRepository();

    private List<Message> messages = new ArrayList<>();

    private MemoryMessageRepository() {}

    @Override
    public void add(Message message) {
        messages.add(message);
    }

    @Override
    public List<Message> get() {
        return new ArrayList<>(messages);
    }
}
