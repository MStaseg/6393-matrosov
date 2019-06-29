package ru.cft.focusstart.matrosov.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InfoMessage extends JsonMessage{
    private final String info;

    @JsonCreator
    public InfoMessage(@JsonProperty("info") String info) {
        this.info = info;
    }

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
