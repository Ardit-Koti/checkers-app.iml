package com.webcheckers.model;

/**
 * instance of game happening contains board
 */
public class Game {

    private Player redPlayer;


    private Player whitePlayer;

    private int gameId = 1; // TODO: 10/6/21  implement number generator for multiple games

    // initilized to red as red goes first


    private Board gameBoard;

    public Game(Player redPlayer, Player whitePlayer) {
        gameBoard = new Board();

        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
    }
    public Board getGameBoard(){return this.gameBoard;}

    public Player getRedPlayer(){return this.redPlayer;}

    public Player getWhitePlayer(){return this.whitePlayer;}

    //todo when a move is made, swap the turn varible


    // TODO: 10/6/21  put into vm from game class: game id, current user viewMode mode options redPlayer.name whitePlayer.name active color




    }
