package com.webcheckers.model;

public class Player {

    public Player(String player_name) {
        this.player_name = player_name;
    }

    private String player_name;

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }
}