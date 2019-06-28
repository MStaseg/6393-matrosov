package ru.cft.focusstart.matrosov.server.repository;

import ru.cft.focusstart.matrosov.common.JsonMessage;

import java.util.ArrayList;
import java.util.List;

public class MemoryMessageRepository implements MessageRepository {

    static final MemoryMessageRepository instance = new MemoryMessageRepository();

    private List<JsonMessage> messages = new ArrayList<>();

    private MemoryMessageRepository() {}

    @Override
    public void add(JsonMessage message) {
        messages.add(message);
    }

    @Override
    public List<JsonMessage> get() {
        return new ArrayList<>(messages);
    }
}
