package com.webcheckers.application;
import com.webcheckers.model.*;

import java.util.HashSet;
import java.util.Iterator;

/**
 * called by board. The reason for this class is to separate the nitty-gritty logic from the board class.
 * the board is the information expert and knows what data is required to validate the move, so it calls the
 * right method in this class. this class takes the information and applies logic to it.
 *
 * hirarchy of move check
 *
 * turn checks to see if its the right time to mvoe
 * board sends info to moveValidationLogic to see if a move is valid
 *
 * this is how it should be used
 *
 * MoveValidator.isDiagonal(Move move)
 * MoveValidator.canDoublejump(info needed)
 * validate.<specific rule to check>(<info needed to check that>) ;
 */
public class MoveValidator {
    private final Game game;
    private final Board board;
    private final Color activeColor;


    public MoveValidator(Game game) {
        this.game = game;
        this.board = game.getGameBoard();
        this.activeColor = game.getColor();
    }


    /**
     * Checks the moves that can be made by the piece at the start position.
     * Precondition: there must be a piece at the start position because it is
     * assumed.
     *
     * @param start the starting position from which a piece is moving.
     * @return an hash set of all simple moves that can be made.
     */
    private HashSet<Move> simpleDiagonalMoves(Position start) {
        HashSet<Move> singleMoves = new HashSet<>();
        Position moveNegPos = Position.createTestPosition(start.getRow() - 1, start.getCell() + 1);
        Position moveNegNeg = Position.createTestPosition(start.getRow() - 1, start.getCell() - 1);
        if(moveNegPos != null && board.getPiece(moveNegPos, activeColor) == null) {
            singleMoves.add(new Move(start, moveNegPos));
        }
        if(moveNegNeg != null && board.getPiece(moveNegNeg, activeColor) == null) {
            singleMoves.add(new Move(start, moveNegNeg));
        }
        return singleMoves;
    }

    /**
     * Checks the moves that can be made by the piece at the start position.
     * Precondition: there must be a piece at the start position because it is
     * assumed.
     *
     * @param start the starting position from which a piece is moving.
     * @return an hash set of all simple moves that can be made.
     */
    private HashSet<Move> kingDiagonalMoves(Position start) {
        HashSet<Move> kingMoves = new HashSet<>();
        Position movePosPos = Position.createTestPosition(start.getRow() + 1, start.getCell() + 1);
        Position movePosNeg = Position.createTestPosition(start.getRow() + 1, start.getCell() - 1);
        Position moveNegPos = Position.createTestPosition(start.getRow() - 1, start.getCell() + 1);
        Position moveNegNeg = Position.createTestPosition(start.getRow() - 1, start.getCell() - 1);
        if(movePosPos != null && board.getPieceAtPosition(movePosPos) == null) {
            kingMoves.add(new Move(start, movePosPos));
        }
        if(movePosNeg != null && board.getPieceAtPosition(movePosNeg) == null) {
            kingMoves.add(new Move(start, movePosNeg));
        }
        if(moveNegPos != null && board.getPieceAtPosition(moveNegPos) == null) {
            kingMoves.add(new Move(start, moveNegPos));
        }
        if(moveNegNeg != null && board.getPieceAtPosition(moveNegNeg) == null) {
            kingMoves.add(new Move(start, moveNegNeg));
        }
        return kingMoves;
    }

    /**
     * Checks the jump moves that can be made by the piece at the start position.
     * Precondition: there must be a piece at the start position because it is
     * assumed.
     *
     * @param start the starting position from which a piece is moving.
     * @return an hash set of all jump moves that can be made.
     */
    private HashSet<Move> simpleJumpMoves(Position start) {
        HashSet<Move> singleMoves = new HashSet<>();
        Position moveNegPos = Position.createTestPosition(start.getRow() - 1, start.getCell() + 1);
        Piece pieceNegPos = board.getPiece(moveNegPos, activeColor);
        Position moveNegNeg = Position.createTestPosition(start.getRow() - 1, start.getCell() - 1);
        Piece pieceNegNeg = board.getPiece(moveNegNeg, activeColor);

        if(moveNegPos != null && pieceNegPos != null && pieceNegPos.getColor() != activeColor) {
            Position jumpNegPos = Position.createTestPosition(start.getRow() - 2, start.getCell() + 2);
            if(jumpNegPos != null && board.getPiece(jumpNegPos, activeColor) == null) {
                singleMoves.add(new Move(start, jumpNegPos));
            }
        }
        if(moveNegNeg != null && pieceNegNeg != null && pieceNegNeg.getColor() != activeColor) {
            Position jumpNegNeg = Position.createTestPosition(start.getRow() - 2, start.getCell() - 2);
            if(jumpNegNeg != null && board.getPiece(jumpNegNeg, activeColor) == null) {
                singleMoves.add(new Move(start, jumpNegNeg));
            }
        }
        return singleMoves;
    }

    /**
     * Checks if you can jump again.
     *
     * @param lastMove a move that is being tested for validation.
     * @return true or false.
     */
    public boolean canJumpContinue(Move lastMove){
        Position endOfLastMove = lastMove.getEnd();
        if(board.getPieceAtPosition(endOfLastMove).getType().equals(Piece.type.SINGLE)) {
            HashSet<Move> allJumpMoves = simpleJumpMoves(endOfLastMove);
            return !allJumpMoves.isEmpty();
        }
        return false;
    }

    /**
     * Tests if the jump can be continued off the first move.
     *
     * @param next the second move that will be made.
     * @param start the first move that was made.
     * @return true or false depending on if the end of the first move matches the
     * start of the second move.
     */
    public boolean isJumpContinued(Move next, Move start){
        Position endOfLastMove = start.getEnd();
        if(board.getPieceAtPosition(endOfLastMove).getType().equals(Piece.type.SINGLE)) {
            HashSet<Move> allJumpMoves = simpleJumpMoves(endOfLastMove);
            return allJumpMoves.contains(next);
        }
        return false;
    }

    /**
     * Validates a move and returns true or false depending on if it is valid or not.
     *
     * @param move a move that is being tested for validation.
     * @return true or false.
     */
    public boolean isMoveValid(Move move){
        //HashSet<Move> allLegalMoves = possibleMoves();
        //return allLegalMoves.contains(move);
        return true;
    }

    /**
     * Uses all the helper methods to get a list of all possible moves that a player can make.
     *
     * @return a  list of all possible moves that a player can make.
     */
    //TODO get ardit's iterator method and update this
    /**
    public HashSet<Move> possibleMoves() {
        HashSet<Move> simpleMoves = new HashSet<>();
        HashSet<Move> jumpMoves = new HashSet<>();

        Iterator<Row> boardItr = board.iterator(activeColor.equals(Color.WHITE));

        while(boardItr.hasNext()) {
            Row row = boardItr.next();
            for(Space space : row.getSpaces()) {
                Piece piece = space.getPiece();
                if(piece != null && piece.getColor().equals(activeColor)) {

                    if(piece.getType() == Piece.type.SINGLE) {
                        simpleMoves.addAll(simpleDiagonalMoves(new Position(row.getIndex(), space.getCellIdx())));
                        jumpMoves.addAll(simpleJumpMoves(new Position(row.getIndex(), space.getCellIdx())));
                    }

                }
            }
        }
        if(jumpMoves.isEmpty()) {
            return simpleMoves;
        }
        else {
            return jumpMoves;
        }
    }
     */



    /**
     * checks if the move is one square in the diagional direction. If a signgle, just checks to see if move
     * is forward. if king, checks for all 4 diagonal
     * @return
     */

     public static boolean isOneDiagonal(Move move){
     int startRow = move.getStart().getRow();
     int startCol = move.getStart().getCell();

     int endRow = move.getEnd().getRow();
     int endCol = move.getEnd().getCell();
     //check the following valid move positions and return true if the position is in one of these places

     // up left
     if (endCol == startCol-1 && endRow == startRow-1)
     return true;
     // up right
     if (endCol == startCol+1 && endRow == startRow-1)
     return true;
     //down left
     if (endCol == startCol-1 && endRow == startRow+1)
     return true;
     //down right
     if (endCol == startCol+1 && endRow ==+1)
     return true;

     return false;
     }


}
