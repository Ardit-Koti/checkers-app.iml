package com.webcheckers.model;

public class Position {
    private int row; //0-7
    private int cell; // the cell in the row (should be called column). 0-7

    public Position(int row, int cell) {
        this.row = row;
        this.cell = cell;
    }

    public int getRow() {
        return row;
    }

    public int getCell() {
        return cell;
    }
}
