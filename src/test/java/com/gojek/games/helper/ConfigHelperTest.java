package com.gojek.games.helper;

import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class ConfigHelperTest {

    @Test
    public void loadInitialConfig() {
        ConfigHelper configHelper = new ConfigHelper();
        configHelper.loadInitialConfig();
        Properties properties = configHelper.getProperties();
        assertEquals("3", properties.getProperty("game.maxGeneration"));
    }
}