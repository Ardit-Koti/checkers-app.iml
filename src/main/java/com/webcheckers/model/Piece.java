package com.webcheckers.model;

public class Piece {



    public enum type
    {
        REGULAR, KING
    }

    public enum color{

        Red, White
    }

    public enum State{
        Alive, Dead
    }



    private final type variety;
    private final color color;
    private State state;


    public Piece(type variety, color color) {
        this.variety = variety;
        this.color = color;
        this.state = State.Alive;

    }

    public void killPiece(){this.state = State.Dead;}
    public type getType(){return this.variety;}
    public color getColor(){return this.color;}
    public State getState(){return this.state;}


}


