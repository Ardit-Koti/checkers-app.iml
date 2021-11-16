package com.webcheckers.application;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.model.PlayerLobby;

import java.util.ArrayList;
import java.util.HashMap;

public class GameCenter {
    private HashMap<Integer, Game > GameList = new HashMap<>();
    private PlayerLobby pLobby;
    private ArrayList<Player> Playing_players;
    public static Integer id;


    public GameCenter() {
        pLobby = new PlayerLobby();
        Playing_players = new ArrayList<>();
        id = 0;
    }
    public HashMap<Integer, Game> getGamesList() {
        return GameList;
    }

    public void listRemove(Game game)
    {
        GameList.remove(game.getGameId());
    }

    public PlayerLobby getPlayerLobby(){return this.pLobby;}

    public Game createGame(Player you, Player opponent){
        Game game = new Game(you, opponent, id);
        GameList.put(id,game);
        id++;
        return game;
    }

    public Game getGame(Integer id){
        return GameList.get(id);
    }






}
