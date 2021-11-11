package com.webcheckers.model;

import java.util.*;

public class Board implements Iterable{
    private final int limit = 7;

    public ArrayList<Row> RowList;

    @Override
    public String toString() {
        return "Board{" +
                "RowList=" + RowList +
                '}';
    }

    @Override
    public Iterator<Row> iterator() {
        return new Iterator<>() {
            int i = -1;
            @Override
            public boolean hasNext() {
                return i+1 <=limit;
            }
            @Override
            public Row next() {
                i = i+1;
                return RowList.get(i);
            }
        };
    }

    public Piece getPiece(Position position, Color perspective) {
        if(position == null || perspective == null) {
            return null;
        }
        if(perspective.equals(Color.WHITE)) {
            return RowList.get(position.getRow()).getPieceAtIndex(position.getCell());
        }
        else {
            return RowList.get(7 - position.getRow()).getPieceAtIndex(7 - position.getCell());
        }
    }



    /**
     * Makes a move on the board.
     *
     * @param move the move that is being made.
     */
    public void makeMove(Move move) {
        int startRow = move.getStart().getRow();
        int startCol = move.getStart().getCell();
        int endRow = move.getEnd().getRow();
        int endCol = move.getEnd().getCell();
        // if the move is a jump, delete the Piece that is jumped over
        if(Math.abs(endRow-startRow) > 1){
            RowList.get((endRow + startRow) / 2).getSpaces().get(endCol + startCol / 2).removeCurrentPiece();
        }

        Piece movingPiece = RowList.get(startRow).getPieceAtIndex(startCol);
        //remove original piece from the board
        RowList.get(startRow).getSpaces().get(startCol).removeCurrentPiece();
        //Add a piece to the new vacant space. Either a king or a single depending on which row it ends on
        if (endRow == 0 || endRow == 7) {
                RowList.get(endRow).getSpaces().get(endCol).addCurrentPiece(new Piece(Piece.type.KING, movingPiece.getColor()));
        }
        else {
            RowList.get(endRow).getSpaces().get(endCol).addCurrentPiece(new Piece(movingPiece.getType(), movingPiece.getColor()));
        }
    }

    /**
     * Gets the piece on a space from the perspective of the player.
     *
     * @param position the position that is being checked.
     * @return the piece at the position.
     */
    public Piece getPieceAtPosition(Position position) {
        if(position == null) {
            return null;
        }
            return RowList.get(position.getRow()).getPieceAtIndex(position.getCell());
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
