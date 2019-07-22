package com.gojek.games;

import com.gojek.games.domain.Cell;
import com.gojek.games.domain.Grid;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.*;

public class GameTest {

    private Properties properties;
    private Game game;

    @Before
    public void setUp() {
        properties = new Properties();
        game = new Game();
    }

    @Test
    public void makeGridWithProperties() {
        properties.setProperty("grid.size.rows", "4");
        properties.setProperty("grid.size.columns", "5");
        game.setProperties(properties);
        game.makeGrid();
        Grid grid = game.getGrid();
        assertEquals(4, grid.getCells().length);
        assertEquals(5, grid.getCells()[0].length);
    }

    @Test
    public void makeGridWithDefaults() {
        game.makeGrid();
        Grid grid = game.getGrid();
        assertEquals(3, grid.getCells().length);
        assertEquals(3, grid.getCells()[0].length);
    }

    @Test
    public void runGenerationWithProperties() {
        properties.setProperty("grid.activeCells", "1,1:2,2");
        game.setProperties(properties);
        game.makeGrid();
        game.prePopulutateCells();

    }

    @Test
    public void prePopulateCellsWithPropertiesOnOneGeneration() throws InterruptedException, IOException {
        properties.setProperty("game.maxGeneration", "1");
        properties.setProperty("grid.activeCells", "1,1:1,0:0,1");
        game.setProperties(properties);
        game.makeGrid();
        game.prePopulutateCells();
        Grid grid = game.getGrid();
        Cell[][] cells = grid.getCells();
        assertFalse(cells[0][0].isAlive());
        assertTrue(cells[1][1].isAlive());
        assertTrue(cells[1][0].isAlive());
        assertTrue(cells[0][1].isAlive());
        game.runGeneration();
        grid = game.getGrid();
        cells = grid.getCells();
        assertTrue(cells[1][1].isAlive());
        assertTrue(cells[1][1].isAlive());
        assertTrue(cells[1][0].isAlive());
        assertTrue(cells[0][1].isAlive());
    }

    @Test
    public void prePopulateCellsWithPropertiesOnTwoGeneration() throws InterruptedException, IOException {
        properties.setProperty("game.maxGeneration", "2");
        properties.setProperty("grid.size.rows", "4");
        properties.setProperty("grid.size.columns", "4");
        properties.setProperty("grid.activeCells", "0,1:1,0:2,0:3,0");
        game.setProperties(properties);
        game.makeGrid();
        game.prePopulutateCells();
        Grid grid = game.getGrid();
        Cell[][] cells = grid.getCells();
        assertTrue(cells[0][1].isAlive());
        assertTrue(cells[1][0].isAlive());
        assertFalse(cells[1][1].isAlive());
        assertTrue(cells[2][0].isAlive());
        assertFalse(cells[2][1].isAlive());
        assertTrue(cells[3][0].isAlive());
        game.runGeneration();
        grid = game.getGrid();
        cells = grid.getCells();
        assertFalse(cells[0][1].isAlive());
        assertTrue(cells[1][0].isAlive());
        assertTrue(cells[1][1].isAlive());
        assertTrue(cells[2][0].isAlive());
        assertTrue(cells[2][1].isAlive());
        assertFalse(cells[3][0].isAlive());
    }

}