package ru.cft.focusstart.matrosov.client.observer;

import ru.cft.focusstart.matrosov.common.Message;

import java.util.List;

public interface MessageObserver {
    void onNewMessage(Message message);
    void onNewMessages(List<Message> messages);
}
