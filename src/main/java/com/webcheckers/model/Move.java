package com.webcheckers.model;

public class Move {
    private Position start;
    private Position end;

    public Move(Position start, Position end) {
        this.start = start;
        this.end = end;
    }

    public Position getStart() {
        return start;
    }

    public Position getEnd() {
        return end;
    }

    /**
     * Checks if simple move is valid
     *
     * @return whether move is valid
     */
    public boolean isSimpleMove() {
        return start.getRow() - end.getRow() == 1 && Math.abs(start.getCell() - end.getCell()) == 1;
    }

    /**
     * Checks if single jump is valid
     *
     * @return whether jump is valid
     */
    public boolean isJumpMove() {
        return Math.abs(start.getRow() - end.getRow()) == 2 && Math.abs(start.getCell() - end.getCell()) == 2;
    }
}
