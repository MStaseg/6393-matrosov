package ru.cft.focusstart.matrosov.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.cft.focusstart.matrosov.entity.User;

public class LoginMessage extends JsonMessage  {
    private final User user;

    @JsonCreator
    public LoginMessage(@JsonProperty("user") User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "LoginMessage{" +
                "user=" + user +
                '}';
    }
}
