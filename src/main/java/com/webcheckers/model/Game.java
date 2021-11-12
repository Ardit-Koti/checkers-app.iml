package com.webcheckers.model;

import com.webcheckers.application.MoveValidator;

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

    private int gameId = 1; // TODO: 10/6/21  implement number generator for multiple games

    // initilized to red as red goes first

    private final MoveValidator validator;
    private Board gameBoard;

    public Game(Player redPlayer, Player whitePlayer) {
        gameBoard = new Board();

        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        this.currentMove = new LinkedList<>();
        validator = new MoveValidator(this);
    }
    public Board getGameBoard(){return this.gameBoard;}

    public Player getRedPlayer(){return this.redPlayer;}

    public Player getWhitePlayer(){return this.whitePlayer;}

    public com.webcheckers.model.Color getColor(){return this.Color;}

    public void setActiveColor(com.webcheckers.model.Color color){this.Color = color;}

    public static final String VALID_MOVE = "You have made your move";
    public static final String JUMP_AGAIN = "You must jump again";
    public static final String ERROR_INVALID_MOVE = "ERROR: invalid move.";


    public boolean isOver(){
        // check if all pieces are dead
        int redPieces = 0;
        int whitePieces = 0;

        Iterator<Row> gameOverRowIterator = gameBoard.iterator();
        for (int i = 0; i < 8; i++) {
            Row tempRow = gameOverRowIterator.next();
            for (int j = 0; j < 8; j++) {
                Iterator<Space> gameOverSpaceIterator = tempRow.iterator();
                Space tempSpace = gameOverSpaceIterator.next();
                if (tempSpace.hasPiece()){
                    if(tempSpace.piece.getColor().equals(com.webcheckers.model.Color.RED))
                        redPieces++;
                    if(tempSpace.piece.getColor().equals(com.webcheckers.model.Color.WHITE))
                        whitePieces++;
                }

            }
        }

        if (redPieces == 0 ){ //white one
            winner = com.webcheckers.model.Color.WHITE;
            return true;
        }

        if (whitePieces==0){
            winner = com.webcheckers.model.Color.RED;
            return true;
        }


        //todo check if no possible moves exist
        return false;
    }

    /**
     * Lets the player take his or her turn.
     *
     * @param move a move that the player is making.
     * @return a string with the results of the attempt to make the move.
     */
    public String takeTurn(Move move) {
        if(currentMove.isEmpty()) {
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

    //todo when a move is made, swap the turn varible


    // TODO: 10/6/21  put into vm from game class: game id, current user viewMode mode options redPlayer.name whitePlayer.name active color




    }
