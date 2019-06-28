package ru.cft.focusstart.matrosov.common;

import java.util.ArrayList;
import java.util.List;

public class MessageList extends JsonMessage {
    private List<Message> messages;

    public MessageList(List<Message> messages) {
        this.messages = messages;
    }

    public MessageList() {}

    public List<Message> getMessages() {
        return new ArrayList<>(messages);
    }

    @Override
    public String toString() {
        return "MessageList{" +
                "messages=" + messages +
                '}';
    }
}
