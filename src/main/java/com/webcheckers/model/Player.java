package com.webcheckers.model;

import java.util.Iterator;

public class Player {
    public enum Color{RED, WHITE}

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

    public void setName(String name) {
        this.name = name;
    }
}
