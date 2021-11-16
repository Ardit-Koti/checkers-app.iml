package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.application.GameCenter;
import com.webcheckers.model.Color;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;


public class PostSpectatorUpdator implements Route{

    final static String JOINED_MESS = "You are spectating successfully";


    private final GameCenter gameCenter;

    private Gson gson;

    private final TemplateEngine templateEngine;

    public PostSpectatorUpdator(Gson gson, GameCenter gameCenter,TemplateEngine templateEngine) {
        this.gameCenter = gameCenter;
        this.templateEngine = templateEngine;
        this.gson = gson;
    }

    @Override
    public String handle(Request request, Response response) {
        final Session httpSession = request.session();
        final Player user = httpSession.attribute("currentUser");

        Game game = user.getGame();
        if (game == null)
        {
            return gson.toJson(Message.info("false"));
        }
        Color currentColor = game.getActiveColor();



        if(user.getColor() == null){
            user.setColor(currentColor);
            return gson.toJson(Message.info(JOINED_MESS));
        }
        if (game.getWinner() != null){
            return gson.toJson(Message.info("true"));
        }

        if(user.getColor().equals(currentColor)){
            return gson.toJson(Message.info("false"));
        }
        else{
            user.setColor(currentColor);
            return gson.toJson(Message.info("true"));
        }
    }
}
