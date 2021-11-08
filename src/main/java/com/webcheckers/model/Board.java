package com.webcheckers.model;

import java.lang.reflect.Array;
import java.util.*;

public class Board{
    private final int redlimit = 7;
    private final int whitelimit = 0;

    public ArrayList<Row> RowList;

    @Override
    public String toString() {
        return "Board{" +
                "RowList=" + RowList +
                '}';
    }
    public Iterator<Row> iterate_by_color(boolean color) {
        if(color) {
            return new Iterator<Row>() {
                int i = -1;
                @Override
                public boolean hasNext() {
                    return i+1 <= redlimit;
                }

                @Override
                public Row next() {
                    i= i+1;
                    return RowList.get(i);
                }
            };
        }
        else{
            return new Iterator<Row>() {
                int a = 8;
                @Override
                public boolean hasNext() {
                    return a-1 >= whitelimit;
                }

                @Override
                public Row next() {
                    a = a-1;
                    return RowList.get(a);
                }
            };
        }
    }

  
    public Board(){
        RowList = new ArrayList<>();
        for(int a = 0; a < 8; a++)
        {
            RowList.add(new Row(a));
        }


        /**
         * generate the 2d array that is the actual board in java

    gameBoard = new Space[8][8];
        for (int rowIndex = 0; rowIndex < 8; rowIndex++) {
            for (int colIndex = 0; colIndex < 8; colIndex++) {
                if ( ((colIndex+rowIndex)%2)==0 ) //row is even
                 gameBoard[rowIndex][colIndex].setShade(Space.shade.DARK);
                else if (((colIndex+rowIndex)%2)!=0 )
                    gameBoard[rowIndex][colIndex].setShade(Space.shade.LIGHT);
            }
        }
         */
        /// TODO: 10/24/21 make the default arrangement of checkers in the baord

    }
  /**
    private void setupGameBoard(){
        int uniquePieceID = 0;
        //place Piece.Color.Red players
        for (int rowIndex = 0; rowIndex < 8; rowIndex++) {
            for (int colIndex = 0; colIndex < 8; colIndex++) {
                // goes over each square
                if (gameBoard[rowIndex][colIndex].getShade().equals(Space.shade.DARK)){ // if the current shade is dark
                    //place a red piece on the first 3 rows (0,1,2) of black squares
                    if (rowIndex<3) {// if in the first 3 rows
                        // create a new piece in current space's current piece field
                        gameBoard[rowIndex][colIndex].setCurrentPiece(new Piece(Piece.Variety.REGULAR, Piece.Color.Red, Piece.State.Alive, uniquePieceID));
                        uniquePieceID++; //as this id has now been taken
                    }
                    // place a white piece on the last 3 (4,6,7) rows of the board
                    if(rowIndex>3){ //if in white peace area and on a black square
                        gameBoard[rowIndex][colIndex].setCurrentPiece(new Piece(Piece.Variety.REGULAR, Piece.Color.White, Piece.State.Alive, uniquePieceID));
                        uniquePieceID++;
                    }
                }
            }
        }
    */
        //place Piece.Color.Black players


    //todo move validation
    }
