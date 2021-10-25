package com.webcheckers.model;

/**
 * instance of game happening contains board
 */
public class Game {

    private Player redPlayer;

    private Player whitePlayer;

    private Piece.Color turn = Piece.Color.Red; // initilized to red as red goes first

    private int gameId = 1; // TODO: 10/6/21  implement unique number generator for multiple games


    Board gameBoard = new Board();

    public Game(Player redPlayer, Player whitePlayer) {
        gameBoard = new Board();

        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
    }

    //todo when a move is made, swap the turn varible


    // TODO: 10/6/21  put into vm from game class: game id, current user viewMode mode options redPlayer.name whitePlayer.name active color




    }
