package com.webcheckers.model;

import java.util.Iterator;

public class Player {

    private boolean inGame;
    private Game game;

    public Player(String player_name, Color color)
    {
        this.name = player_name;
        this.color = color;
    }


    public Player(String name){this.name = name;}


    private String name;
    private Color color;

    public void setColor(Color color){this.color = color;}
    public String getName() {
        return this.name;
    }
    public Color getColor(){return this.color;}

    public boolean isInGame(){return inGame;}

    public void setInGame(){inGame = true;}

    public void leaveGame(){inGame = false;}

    public Game getGame(){return this.game;}

    public void setGame(Game game){this.game = game;}

    public void setName(String name) {
        this.name = name;
    }


}
