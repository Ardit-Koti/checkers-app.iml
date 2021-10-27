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


    public Piece(type variety, color color) {
        this.variety = variety;
        this.color = color;

    }

    public type getType(){return this.variety;}
    public color getColor(){return this.color;}

}


