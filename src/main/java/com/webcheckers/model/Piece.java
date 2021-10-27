package com.webcheckers.model;

public class Piece {



    public enum type
    {
        REGULAR, KING
    }

    public enum color{

        RED, WHITE
    }

    public enum State{
        Alive, Dead
    }



    private final type Type;
    private final color color;
    private State state;


    public Piece(type Type, color color) {
        this.Type = Type;
        this.color = color;
        this.state = State.Alive;

    }

    public void killPiece(){this.state = State.Dead;}
    public type getType(){return this.Type;}
    public color getColor(){return this.color;}
    public State getState(){return this.state;}


}


