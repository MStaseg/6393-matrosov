package ru.cft.focusstart.matrosov.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Message.class, name = "Message"),
        @JsonSubTypes.Type(value = ClientMessage.class, name = "Client"),
        @JsonSubTypes.Type(value = ClientList.class, name = "ClientList"),
        @JsonSubTypes.Type(value = MessageList.class, name = "MessageList"),
        @JsonSubTypes.Type(value = ErrorMessage.class, name = "ErrorMessage"),
        @JsonSubTypes.Type(value = InfoMessage.class, name = "InfoMessage"),
        @JsonSubTypes.Type(value = SuccessMessage.class, name = "SuccessMessage")}
)
public abstract class JsonMessage {
    public JsonMessage() {}
}
