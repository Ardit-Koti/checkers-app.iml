package com.webcheckers.model;

import com.webcheckers.application.MoveValidator;

import java.util.LinkedList;

/**
 * instance of game happening contains board
 */
public class Game {

    private Player redPlayer;

    private LinkedList<Move> currentMove;

    private Player whitePlayer;

    private com.webcheckers.model.Color Color;

    private int gameId = 1; // TODO: 10/6/21  implement number generator for multiple games

    // initilized to red as red goes first

    private MoveValidator validator;
    private Board gameBoard;

    public Game(Player redPlayer, Player whitePlayer) {
        gameBoard = new Board();

        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        this.currentMove = new LinkedList<>();
    }
    public Board getGameBoard(){return this.gameBoard;}

    public Player getRedPlayer(){return this.redPlayer;}

    public Player getWhitePlayer(){return this.whitePlayer;}

    public com.webcheckers.model.Color getColor(){return this.Color;}

    public void setActiveColor(com.webcheckers.model.Color color){this.Color = color;}

    /**
     * Lets the player take his or her turn.
     *
     * @param move a move that the player is making.
     * @return a string with the results of the attempt to make the move.
     */


    //todo when a move is made, swap the turn varible


    // TODO: 10/6/21  put into vm from game class: game id, current user viewMode mode options redPlayer.name whitePlayer.name active color




    }
