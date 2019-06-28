package ru.cft.focusstart.matrosov.server.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonParser {

    private static JsonParser instance;

    private ObjectMapper mapper;

    public static synchronized JsonParser getInstance() {
        if (instance == null) {
            instance = new JsonParser();
        }

        return instance;
    }

    private JsonParser() {
        JsonFactory jsonFactory = new JsonFactory();
        jsonFactory.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
        mapper = new ObjectMapper(jsonFactory);
    }

    public <T> T readValue(String content, Class<T> valueType)
            throws IOException, JsonParseException, JsonMappingException {
        return mapper.readValue(content, valueType);
    }

    public ObjectMapper getMapper() {
        return mapper;
    }
}
