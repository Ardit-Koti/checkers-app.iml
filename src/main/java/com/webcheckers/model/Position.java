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

    /**
     * Creates a new position only if the parameters are correct.
     *
     * @param row an int of any size.
     * @param cell an int of any size.
     * @return a new position with row and cell or null if the inputs are wrong.
     */
    public static Position createTestPosition(int row, int cell) {
        if(row < 8 && row >= 0 && cell < 8 && cell >= 0) {
            return new Position(row, cell);
        }
        return null;
    }
}
