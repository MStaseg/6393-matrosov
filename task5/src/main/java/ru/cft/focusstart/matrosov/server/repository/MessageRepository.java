package ru.cft.focusstart.matrosov.server.repository;

import ru.cft.focusstart.matrosov.common.JsonMessage;
import ru.cft.focusstart.matrosov.common.Message;

import java.util.List;

public interface MessageRepository {
    void add(JsonMessage message);
    List<JsonMessage> get();
}
