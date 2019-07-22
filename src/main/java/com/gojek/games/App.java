package com.gojek.games;

import com.gojek.games.exception.ConfigException;
import com.gojek.games.helper.ConfigHelper;

import java.io.IOException;
import java.util.Properties;

public class App {

    public static void main(String[] args) throws InterruptedException, IOException {
        try {
            ConfigHelper configHelper = new ConfigHelper();
            configHelper.loadInitialConfig();
            Properties properties = configHelper.getProperties();
            Game game = new Game();
            game.setProperties(properties);
            game.makeGrid();
            game.prePopulutateCells();
            game.runGeneration();
        } catch (ConfigException e) {
            System.out.println(e.getMessage());
        }
    }
}
