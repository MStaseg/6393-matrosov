package ru.cft.focusstart.matrosov.common;

import java.util.ArrayList;
import java.util.List;

public class ClientList extends JsonMessage {
    private List<ClientMessage> messages;

    public ClientList(List<ClientMessage> messages) {
        this.messages = messages;
    }

    public ClientList() {}

    public List<ClientMessage> getMessages() {
        return new ArrayList<>(messages);
    }

    @Override
    public String toString() {
        return "ClientList{" +
                "messages=" + messages +
                '}';
    }
}
