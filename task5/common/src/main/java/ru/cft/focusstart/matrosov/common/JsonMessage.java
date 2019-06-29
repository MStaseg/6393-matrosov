package ru.cft.focusstart.matrosov.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChatMessage.class, name = "ChatMessage"),
        @JsonSubTypes.Type(value = LoginMessage.class, name = "LoginMessage"),
        @JsonSubTypes.Type(value = ClientListMessage.class, name = "ClientListMessage"),
        @JsonSubTypes.Type(value = LoginFailMessage.class, name = "LoginFailMessage"),
        @JsonSubTypes.Type(value = InfoMessage.class, name = "InfoMessage"),
        @JsonSubTypes.Type(value = LoginSuccessMessage.class, name = "LoginSuccessMessage")}
)
public abstract class JsonMessage {
    public JsonMessage() {}
}
