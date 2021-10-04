package com.webcheckers.model;

public class Board {
    private Space[][] gameboard;

    public Board(){
    }

    public Space getSpace(int row, int col){
        return gameboard[row][col];
    }
}
