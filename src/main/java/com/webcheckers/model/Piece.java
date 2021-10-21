package com.webcheckers.model;

public abstract class Piece {
    public enum Variety {
        REGULAR, KING
    }

    public enum Status{
        OPEN, JUMP, INVALID
    }

    // idk what to do for shade of piece

    private final Variety variety;
    private Status status;

    public Piece(Variety variety, Status status) {
        this.variety = variety;
        this.status = status;
    }

}
