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
}
