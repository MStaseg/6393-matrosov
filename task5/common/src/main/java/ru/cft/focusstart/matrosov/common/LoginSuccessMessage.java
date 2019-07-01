package ru.cft.focusstart.matrosov.common;

import com.fasterxml.jackson.annotation.JsonCreator;

public class LoginSuccessMessage extends JsonMessage  {

    @JsonCreator
    public LoginSuccessMessage() {}

    @Override
    public String toString() {
        return "LoginSuccessMessage";
    }
}
