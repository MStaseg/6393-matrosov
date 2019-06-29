package ru.cft.focusstart.matrosov.server.repository;

import ru.cft.focusstart.matrosov.common.JsonMessage;

import java.util.List;

/**
 * Basic interface for class holding chat messages
 */
public interface MessageRepository {

    /**
     * Adds the new message to the repo
     *
     * @param message new message
     */
    void add(JsonMessage message);

    /**
     * Use this method to get all current persistent messages
     *
     * @return list of messages
     */
    List<JsonMessage> get();
}
