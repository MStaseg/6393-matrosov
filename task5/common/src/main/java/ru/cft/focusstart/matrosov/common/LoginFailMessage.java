package ru.cft.focusstart.matrosov.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginFailMessage extends JsonMessage {
    private final String cause;

    @JsonCreator
    public LoginFailMessage(@JsonProperty("cause") String cause) {
        this.cause = cause;
    }

    public String getCause() {
        return cause;
    }

    @Override
    public String toString() {
        return "LoginFailMessage{" +
                "cause='" + cause + '\'' +
                '}';
    }
}
