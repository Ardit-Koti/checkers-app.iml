package com.webcheckers.application;
import com.webcheckers.model.*;
import com.webcheckers.ui.*;

/**
 * called by board. The reason for this class is to separate the nitty-gritty logic from the board class.
 * the board is the information expert and knows what data is required to validate the move, so it calls the
 * right method in this class. this class takes the information and applies logic to it.
 *
 * hierarchy of move check
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


    /**
     * checks if the move is one square in the diagional direction. If a signgle, just checks to see if move
     * is forward. if king, checks for all 4 diagonal
     * @return
     */
    public static boolean isOneDiagonal(Move move){
        int startRow = move.getStartPos().getRow();
        int startCol = move.getStartPos().getCell();

        int endRow = move.getEndPos().getRow();
        int endCol = move.getEndPos().getCell();
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
