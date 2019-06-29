package ru.cft.focusstart.matrosov.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.cft.focusstart.matrosov.entity.User;

import java.util.ArrayList;
import java.util.List;

public class ClientListMessage extends JsonMessage {

    private final List<User> users;

    @JsonCreator
    public ClientListMessage(@JsonProperty("users") List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return new ArrayList<>(users);
    }

    @Override
    public String toString() {
        return "ClientListMessage{" +
                "users=" + users +
                '}';
    }
}
