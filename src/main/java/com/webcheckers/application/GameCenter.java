package com.webcheckers.application;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.model.PlayerLobby;

import java.util.ArrayList;
import java.util.HashMap;

public class GameCenter {
    private ArrayList<Game> listGame = new ArrayList<>();
    private PlayerLobby pLobby;
    private HashMap<Player, Player> spectators;


    public GameCenter( PlayerLobby pLobby ) {
        this.pLobby = pLobby;
        spectators = new HashMap<>();
    }
    public ArrayList<Game> getGamesList() {
        return listGame;
    }

    public void listRemove(Game game){
        listGame.remove(game);
    }

    public boolean spectatorFinder(Player player){
        return spectators.containsKey(player);
    }





}
