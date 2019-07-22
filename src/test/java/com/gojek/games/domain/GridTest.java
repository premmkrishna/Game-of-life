package com.gojek.games.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GridTest {

    private Grid grid;

    @Before
    public void init() {
        grid = new Grid();
    }

    @Test
    public void testPrePopulateWithConfigurationMustPrePopulateOnCellsProvided() {
        String[] initialPositions = {"1,1", "2,2", "0,0"};
        grid.prePopulateCells(initialPositions);
        Cell[][] cells = grid.getCells();
        assertTrue(cells[0][0].isAlive());
        assertTrue(cells[1][1].isAlive());
        assertTrue(cells[2][2].isAlive());
    }

    @Test
    public void nextGenerationMustMakeCornerCellsAliveOn3AliveNeighbours() {
        String[] initialPositions = {"0,1", "1,1", "1,0"};
        grid.prePopulateCells(initialPositions);
        Cell[][] cells = grid.getCells();
        assertFalse(cells[0][0].isAlive());
        grid.nextGeneration();
        cells = grid.getCells();
        assertTrue(cells[0][0].isAlive());
    }

    @Test
    public void nextGenerationMustMakeCornerCellsDeadOnUnderPopulation() {
        String[] initialPositions = {"0,1", "0,0"};
        grid.prePopulateCells(initialPositions);
        Cell[][] cells = grid.getCells();
        assertTrue(cells[0][0].isAlive());
        grid.nextGeneration();
        cells = grid.getCells();
        assertFalse(cells[0][0].isAlive());
    }

    @Test
    public void nextGenerationMustMakeCellsDeadOnOverPopulation() {
        String[] initialPositions = {"1,1", "0,0", "1,0", "0,1", "2,2"};
        grid.prePopulateCells(initialPositions);
        Cell[][] cells = grid.getCells();
        assertTrue(cells[1][1].isAlive());
        grid.nextGeneration();
        cells = grid.getCells();
        assertFalse(cells[1][1].isAlive());
    }

    @Test
    public void nextGenerationMustKeepCellsAliveOnOptimalPopulation() {
        String[] initialPositions = {"1,1", "0,0", "1,0", "0,1"};
        grid.prePopulateCells(initialPositions);
        Cell[][] cells = grid.getCells();
        assertTrue(cells[1][1].isAlive());
        grid.nextGeneration();
        cells = grid.getCells();
        assertTrue(cells[1][1].isAlive());
    }


    @Test
    public void testAllNeighboursAliveMustReturn2() {
        List<Cell> neighbours = new ArrayList<>();
        neighbours.add(new Cell(true));
        neighbours.add(new Cell(true));
        int aliveNeighbours = grid.getAliveNeighbours(neighbours);
        assertEquals(2, aliveNeighbours);
    }

    @Test
    public void testOneNeighboursAliveMustReturn1() {
        List<Cell> neighbours = new ArrayList<>();
        neighbours.add(new Cell(true));
        neighbours.add(new Cell(false));
        int aliveNeighbours = grid.getAliveNeighbours(neighbours);

        assertEquals(1, aliveNeighbours);
    }

    @Test
    public void testAllDeadNeighboursAliveMustReturn0() {
        List<Cell> neighbours = new ArrayList<>();
        neighbours.add(new Cell(false));
        neighbours.add(new Cell(false));
        int aliveNeighbours = grid.getAliveNeighbours(neighbours);
        assertEquals(0, aliveNeighbours);
    }
}