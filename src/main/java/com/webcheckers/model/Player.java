package com.webcheckers.model;

import java.util.Iterator;

public class Player {

    public Player(String player_name) {
        this.name = player_name;
    }

    private String name;

    public Player() {

    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
