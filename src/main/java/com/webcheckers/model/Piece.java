package com.webcheckers.model;

public class Piece {

    public enum Variety
    {
        REGULAR, KING
    }

    public enum Color{

        Red, White
    }

    public enum Status
    {
        OPEN, JUMP, INVALID
    }
    public enum State{
        Alive, Dead
    }



    private final Variety variety;
    private final Color color;
    private State currentState;


    public Piece(Variety variety, Color color, State currentState) {
        this.variety = variety;
        this.color = color;
        this.currentState = currentState;

    }

    public Variety getType(){return this.variety;}
    public Color getColor(){return this.color;}

}


