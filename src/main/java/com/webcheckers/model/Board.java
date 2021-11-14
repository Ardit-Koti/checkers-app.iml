package com.webcheckers.model;

import java.util.*;

public class Board{
    private final int redlimit = 7;
    private final int whitelimit = 0;

    public ArrayList<Row> RowList;
    private List<Move> moves = new ArrayList<>();


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


    public Piece getPiece(Position position, Color perspective) {
        if(position == null || perspective  == null) {
            return null;
        }
        if(perspective.equals(Color.RED)) {
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
        int startRow = move.getStartPos().getRow();
        int startCol = move.getStartPos().getCell();
        int endRow = move.getEndPos().getRow();
        int endCol = move.getEndPos().getCell();
        // if the move is a jump, delete the Piece that is jumped over
        if(move.isJumpMove()){
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

    public void addMove(Move m){
        moves.add(m);
    }
    public List<Move> getMoves(){
        return moves;
    }

    }
