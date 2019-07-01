package ru.cft.focusstart.matrosov.server.repository;

import ru.cft.focusstart.matrosov.common.JsonMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MemoryMessageRepository implements MessageRepository {

    static final MemoryMessageRepository INSTANCE = new MemoryMessageRepository();

    private final List<JsonMessage> messages = new CopyOnWriteArrayList<>();

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
