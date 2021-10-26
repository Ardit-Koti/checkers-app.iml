package com.webcheckers.model;

public class Piece {
    public enum Type {
        REGULAR, KING
    }

    public enum Color{
        OPEN, JUMP, INVALID
    }

    private final Type type;
    private final Color color;

    public Piece(Type type, Color color) {
        this.type = type;
        this.color = color;
    }

    public Type getType(){return this.type;}
    public Color getColor(){return this.color;}

}
