package ru.cft.focusstart.matrosov.client.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    public ObjectMapper getMapper() {
        return mapper;
    }

}
