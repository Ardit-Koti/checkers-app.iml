package com.webcheckers.model;

public class Piece {



    public enum type
    {
        SINGLE, KING
    }

    public enum color{

        RED, WHITE
    }

    public enum State{
        Alive, Dead
    }



    private final type Type;
    private final color Color;
    private State state;


    public Piece(type Type, color Color) {
        this.Type = Type;
        this.Color = Color;
        this.state = State.Alive;

    }

    public void killPiece(){this.state = State.Dead;}
    public type getType(){return this.Type;}
    public color getColor(){return this.Color;}
    public State getState(){return this.state;}


}


