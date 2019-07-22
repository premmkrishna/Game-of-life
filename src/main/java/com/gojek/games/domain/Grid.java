package com.gojek.games.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.gojek.games.constants.Constants.directions;

public class Grid {
    private final int rows;
    private final int columns;
    private final Random random = new Random();
    private Cell[][] cells;


    public Grid() {
        this.rows = 3;
        this.columns = 3;
        cells = new Cell[3][3];
    }

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.columns = cols;
        cells = new Cell[rows][cols];
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void prePopulateCells(String[] initialPositions) {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                cells[row][column] = new Cell();
            }
        }
        for (String postion : initialPositions) {
            String[] split = postion.split(",");
            int row = Integer.parseInt(split[0]);
            int column = Integer.parseInt(split[1]);
            cells[row][column].setAlive(true);
        }
    }

    private boolean getRandomBoolean() {
        return random.nextBoolean();
    }

    public void prePopulateCells() {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                cells[row][column] = new Cell(getRandomBoolean());
            }
        }
    }

    private void initGrid(Cell[][] cells) {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                cells[row][column] = new Cell();
            }
        }
    }

    public ArrayList<Cell> getNeighbouringCells(int row, int column) {
        ArrayList<Cell> neighbours = new ArrayList<>();
        for (int[] direction : directions) {
            Cell neighbour = getNeighbouringCell(row + direction[0], column + direction[1]);
            if (neighbour != null) {
                neighbours.add(neighbour);
            }
        }
        return neighbours;
    }

    private Cell getNeighbouringCell(int row, int column) {
        if (row >= 0 && column >= 0 && row < cells.length && column < cells[0].length) {
            return cells[row][column];
        }
        return null;
    }

    public int getAliveNeighbours(List<Cell> neighbours) {
        int aliveNeighbours = 0;
        for (Cell neighbour : neighbours) {
            if (neighbour.isAlive()) {
                aliveNeighbours++;
            }
        }
        return aliveNeighbours;
    }

    public void nextGeneration() {
        Cell[][] newGenerationCells = new Cell[cells.length][cells[0].length];
        initGrid(newGenerationCells);
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Cell newGenerationCell = newGenerationCells[row][column];
                ArrayList<Cell> neighbouringCells = getNeighbouringCells(row, column);
                int aliveNeighbours = getAliveNeighbours(neighbouringCells);
                Cell oldGenerationCell = cells[row][column];
                if (oldGenerationCell.isAlive()) {
                    if ((aliveNeighbours == 2) || (aliveNeighbours == 3)) {
                        newGenerationCell.setAlive(true);
                    } else {
                        newGenerationCell.setAlive(false);
                    }
                } else {
                    if (aliveNeighbours == 3) {
                        newGenerationCell.setAlive(true);
                    }
                }
            }
        }
        cells = newGenerationCells;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                sb.append(cells[row][column]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
