package ru.cft.focusstart.matrosov.model;

import java.util.Properties;

public class PropertyManager {
    private final static String CONFIG_FILE_NAME = "/game_settings.properties";

    private static PropertyManager instance;

    private PropertyManager() {

    }

    public static synchronized PropertyManager getInstance() {
        if (instance == null) {
            instance = new PropertyManager();
        }
        return instance;
    }
}
