package com.gojek.games;

import com.gojek.games.domain.Grid;

import java.io.IOException;
import java.util.Properties;


class Game {

    private Grid grid;

    private Properties properties;

    Game() {
        this.properties = new Properties();
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Grid getGrid() {
        return grid;
    }


    void prePopulutateCells() {
        String activeCells = properties.getProperty("grid.activeCells");
        if (activeCells != null) {
            String[] split = activeCells.split(":");
            grid.prePopulateCells(split);
        } else {
            grid.prePopulateCells();
        }
    }

    void makeGrid() {
        String rows = properties.getProperty("grid.size.rows");
        String columns = properties.getProperty("grid.size.columns");
        if (rows == null || columns == null) {
            grid = new Grid();
        } else {
            grid = new Grid(Integer.parseInt(rows), Integer.parseInt(columns));
        }
    }

    private void clearScreen() throws IOException {
        final String os = System.getProperty("os.name");
        if (os.contains("Windows")) {
            Runtime.getRuntime().exec("cls");
        } else {
            Runtime.getRuntime().exec("clear");
        }

    }

    void runGeneration() throws InterruptedException, IOException {
        String generationSize = properties.getProperty("game.maxGeneration");
        long maxGenerations = Long.MAX_VALUE;
        long currentGeneration = 0;
        boolean isGenerationsLimited = false;
        if (generationSize != null) {
            maxGenerations = Integer.parseInt(generationSize);
            isGenerationsLimited = true;
        }
        while (!isGenerationsLimited || currentGeneration < maxGenerations) {
            System.out.printf("Current generation %d %n", currentGeneration);
            System.out.println(grid);
            System.out.println();
            grid.nextGeneration();
            Thread.sleep(1000);
            clearScreen();
            currentGeneration++;
        }
    }
}
