package ru.cft.focusstart.matrosov.common;

public class InfoMessage extends JsonMessage{
    private String info;

    public InfoMessage(String info) {
        this.info = info;
    }

    public InfoMessage() {}

    public String getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "InfoMessage{" +
                "info='" + info + '\'' +
                '}';
    }
}
