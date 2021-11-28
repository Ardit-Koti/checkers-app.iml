package com.webcheckers.model;


/**
 * class that represents a piece  on the checkers board
 */
public class Piece {



    public enum type
    {
        SINGLE, KING
    }



    public enum State{
        Alive, Dead
    }



    private final type Type;
    private final Color Color;
    private State state;


    public Piece(type Type, Color Color) {
        this.Type = Type;
        this.Color = Color;
        this.state = State.Alive;

    }

    public Piece(type Type, Color Color, State state) {
        this.Type = Type;
        this.Color = Color;
        this.state = state;

    }

    public void killPiece(){this.state = State.Dead;}
    public type getType(){return this.Type;}
    public Color getColor(){return this.Color;}
    public State getState(){return this.state;}

    public Piece copyPiece(){ return new Piece(getType(), getColor(), getState()); }


}


