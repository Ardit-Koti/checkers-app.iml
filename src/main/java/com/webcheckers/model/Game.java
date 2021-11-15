package com.webcheckers.model;

import com.webcheckers.application.MoveValidator;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * instance of game happening contains board
 */
public class Game {

    private Player redPlayer;

    private LinkedList<Move> currentMove;

    private Player whitePlayer;

    private Color winner;

    private com.webcheckers.model.Color Color;

    private Color activeColor;

    private int gameId = 1; // TODO: 10/6/21  implement number generator for multiple games

    // initilized to red as red goes first

    //private boolean isOver;

    private final MoveValidator validator;

    private Board gameBoard;

    private Board futureBoard;

    private LinkedList<Board> boardList;

    public Game(Player redPlayer, Player whitePlayer) {
        this.gameBoard = new Board();
        this.futureBoard = this.gameBoard;
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        this.currentMove = new LinkedList<>();
        this.boardList = new LinkedList<Board>();
        boardList.add(new Board(this.gameBoard));
        validator = new MoveValidator(this);
    }

    public Board getGameBoard(){return this.gameBoard;}

    public Board getFutureBoard(){return this.futureBoard;}

    public Player getRedPlayer(){return this.redPlayer;}

    public Player getWhitePlayer(){return this.whitePlayer;}

    public com.webcheckers.model.Color getColor(){return this.Color;}


    public static final String VALID_MOVE = "You have made your move";
    public static final String JUMP_AGAIN = "You must jump again";
    public static final String ERROR_INVALID_MOVE = "ERROR: invalid move.";
    public static final String ERROR_NO_MOVE = "ERROR: You have not made a move to submit";
    public static final String ERROR_MORE_JUMPS = "ERROR: You can chain more jumps";
    public static final String SUBMITTED = "Move submitted!";
    public static final String ERROR_NO_MOVE_BACKUP = "ERROR: You have not made a move to backup from.";
    public static final String BACKUP_SUCCESS = "Move backed up.";

    /**
     * checks if the game is over. This check should occur before each move.
     *
     * if a game is over, the winner object is set.
     *
     * @return
     */
    public boolean isOver(){
        // check if all pieces are dead
        int redPieces = 0;
        int whitePieces = 0;

        // TODO: 11/14/21 add return true for resign.

        Iterator<Row> gameOverRowIterator = gameBoard.iterate_by_color(true);
        while (gameOverRowIterator.hasNext()) {
            Row tempRow = gameOverRowIterator.next();
            Iterator<Space> gameOverSpaceIterator = tempRow.iterate_by_color(true);
            while (gameOverSpaceIterator.hasNext()) {
                Space tempSpace = gameOverSpaceIterator.next();
                if (tempSpace.hasPiece()){
                    if(tempSpace.getPiece().getColor() == (com.webcheckers.model.Color.RED))
                        redPieces++;
                    if(tempSpace.getPiece().getColor() == (com.webcheckers.model.Color.WHITE))
                        whitePieces++;
                }

            }
        }

        if (redPieces == 0 ){ //white won
            System.out.println("zero red Pieces");
            winner = com.webcheckers.model.Color.WHITE;
            return true;
        }

        if (whitePieces==0){ // red won
            System.out.println("zero white Pieces");
            winner = com.webcheckers.model.Color.RED;
            return true;
        }


        //todo check if no possible moves exist

        return false; // game is not over
    }


    public void declareWinner(Color winnerColor){
        this.winner= winnerColor;
    }

        public String submitMove() {

            if(this.isOver()){
                System.out.print(" WINNER WINNER CHICKEN DINNER!! Congratulations ");
                if (this.winner == com.webcheckers.model.Color.RED)
                    System.out.println("Red player");
                if (this.winner == com.webcheckers.model.Color.WHITE)
                    System.out.println("WHITE player");
                    
            }




            if (currentMove.isEmpty()) {
                return ERROR_NO_MOVE;
            } else {
                if (currentMove.getLast().isJumpMove()) {
                    if (validator.canJumpContinue(currentMove.getLast())) {
                        return ERROR_MORE_JUMPS;
                    }
                }
                if (activeColor.equals(com.webcheckers.model.Color.RED)) {setActiveColor(com.webcheckers.model.Color.WHITE);}
                else {setActiveColor(com.webcheckers.model.Color.RED);}
                currentMove.clear();
                this.boardList.add(new Board(this.gameBoard));
                return SUBMITTED;
            }
        }

    /**
     * Lets the player take his or her turn.
     *
     * @param move a move that the player is making.
     * @return a string with the results of the attempt to make the move.
     */
    public String takeTurn(Move move) {


        if(currentMove.isEmpty()) { // if there is no move
            boolean isValid = validator.isMoveValid(move);
            if(isValid) {
                currentMove.add(move);
                gameBoard.makeMove(move);
                if(move.isJumpMove() && validator.canJumpContinue(move)) {
                    return JUMP_AGAIN;
                }
                else {
                    return VALID_MOVE;
                }
            }
            else {
                return ERROR_INVALID_MOVE;
            }
        }
        else {
            boolean partOfJump = false;
            if(currentMove.getLast().isJumpMove()) {
                //System.out.println("ENTERING CHAINING");
                partOfJump = validator.isJumpContinued(move, currentMove.getLast());
            }
            if(partOfJump) {
                boolean isValid = validator.canJumpContinue(currentMove.getLast());
                if (isValid) {
                    currentMove.add(move);
                    gameBoard.makeMove(move);
                    if (validator.canJumpContinue(move)) {
                        return JUMP_AGAIN;
                    }
                    return VALID_MOVE;
                }
                else {
                    return ERROR_INVALID_MOVE;
                }
            }
            else {
                return ERROR_INVALID_MOVE;
            }
        }
    }

    public void setActiveColor(Color activeColor){this.activeColor = activeColor;}

    public Color getActiveColor(){return this.activeColor;}

    public void setGameBoard(Board gameBoard){this.gameBoard = gameBoard;}

    public String backup()
    {
        if(this.currentMove.isEmpty())
        {
            return ERROR_NO_MOVE_BACKUP;
        }
        else
        {
            this.gameBoard = boardList.getLast();
            currentMove.clear();
            return BACKUP_SUCCESS;
        }
    }

    public String resignGame(Color color){
        return "FooBar";

    }



    //todo when a move is made, swap the turn varible



    // TODO: 10/6/21  put into vm from game class: game id, current user viewMode mode options redPlayer.name whitePlayer.name active color


    }
