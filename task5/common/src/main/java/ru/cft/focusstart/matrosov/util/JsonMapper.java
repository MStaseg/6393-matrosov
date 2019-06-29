package ru.cft.focusstart.matrosov.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.cft.focusstart.matrosov.common.JsonMessage;

import java.io.IOException;

public class JsonMapper {
    public static final JsonMapper instance = new JsonMapper();

    private ObjectMapper mapper;

    private JsonMapper() {
        mapper = new ObjectMapper();
    }

    public JsonMessage mapToMessage(String value) throws IOException {
        return mapper.readValue(value, JsonMessage.class);
    }

    public String mapToString(JsonMessage value) throws IOException {
        return mapper.writeValueAsString(value);
    }
}
