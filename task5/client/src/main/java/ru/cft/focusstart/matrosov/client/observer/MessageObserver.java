package ru.cft.focusstart.matrosov.client.observer;

import ru.cft.focusstart.matrosov.common.ChatMessage;

public interface MessageObserver {
    void onNewMessage(ChatMessage message);
}
