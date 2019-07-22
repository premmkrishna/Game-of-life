package com.gojek.games.domain;

import com.gojek.games.constants.Constants;

public class Cell {

    private boolean alive;
    private int lastLivedGeneration;

    Cell() {
        alive = false;
        lastLivedGeneration = -1;
    }

    Cell(boolean alive) {
        lastLivedGeneration = -1;
        this.alive = alive;
    }


    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    @Override
    public String toString() {
        if (alive) {
            return Constants.LIVE_CELL;
        }
        return Constants.DEAD_CELL;
    }
}
