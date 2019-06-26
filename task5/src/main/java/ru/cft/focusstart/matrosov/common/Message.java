package ru.cft.focusstart.matrosov.common;

import java.util.Date;

public class Message {
    private String text;
    private Date date;
    private String from;

    public Message(String text, Date date, String from) {
        this.text = text;
        this.date = date;
        this.from = from;
    }

    public  Message() {}

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public String getFrom() {
        return from;
    }
}
