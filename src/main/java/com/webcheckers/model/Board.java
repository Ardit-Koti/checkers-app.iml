package com.webcheckers.model;

public class Board {
    private Space[][] gameboard;

    public Space getSpace(int row, int col){
        return gameboard[row][col];
    }
}
