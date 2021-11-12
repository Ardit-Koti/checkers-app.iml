package com.webcheckers.model;

public class Move {
    private Position start;
    private Position end;

    public Move(Position start, Position end) {
        this.start = start;
        this.end = end;
    }

    public Position getStartPos() {
        return start;
    }

    public Position getEndPos() {
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

    @Override
    public boolean equals(Object o){
        if (this == o) {return true;}
        if (o instanceof Move){
            Move other = (Move)o;
            return other.getStartPos().equals(getStartPos()) && other.getEndPos().equals(getEndPos());
        }
        return false;
    }
}
