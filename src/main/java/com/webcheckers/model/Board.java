package com.webcheckers.model;

public class Board {
    // [row] [col]
    private Space[][] gameBoard;



    public Board(){
    gameBoard = new Space[8][8];
        for (int rowIndex = 0; rowIndex < 8; rowIndex++) {

            for (int colIndex = 0; colIndex < 8; colIndex++) {
                if ( ((colIndex+rowIndex)%2)==0 ) //row is even
                 gameBoard[rowIndex][colIndex].setShade(Space.shade.DARK);
                else if (((colIndex+rowIndex)%2)!=0 )
                    gameBoard[rowIndex][colIndex].setShade(Space.shade.LIGHT);
            }

        }


    }
    public Space getSpace(int row, int col){
        return gameBoard[row][col];
    }
// olafs change to hi

}
