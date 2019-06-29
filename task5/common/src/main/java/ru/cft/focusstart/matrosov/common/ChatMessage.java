package ru.cft.focusstart.matrosov.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.cft.focusstart.matrosov.entity.User;

import java.util.Date;

public class ChatMessage extends JsonMessage {
    private final String text;
    private final Date date;
    private final User from;

    @JsonCreator
    public ChatMessage(@JsonProperty("text") String text,
                       @JsonProperty("date") Date date,
                       @JsonProperty("from") User from) {
        this.text = text;
        this.date = date;
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public User getFrom() {
        return from;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "text='" + text + '\'' +
                ", date=" + date +
                ", from='" + from + '\'' +
                '}';
    }
}
