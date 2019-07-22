package com.gojek.games.helper;

import com.gojek.games.exception.ConfigException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigHelper {
    private final Properties properties = new Properties();

    public void loadInitialConfig() {
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(is);
        } catch (IOException e) {
            throw new ConfigException("Error loading configuration for game from application.properties.Please ensure the file exists");
        }
    }

    public Properties getProperties() {
        return properties;
    }
}
