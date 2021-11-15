package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.application.GameCenter;
import com.webcheckers.model.Color;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

class PostCheckTurnRoute implements Route {

    private final Gson gson;
    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;

    public PostCheckTurnRoute(final TemplateEngine templateEngine, GameCenter gameCenter, final Gson gson) {
        this.gson = gson;
        this.templateEngine = templateEngine;
        this.gameCenter = gameCenter;
    }


    @Override
    public String handle(Request request, Response response) {
        final Session httpSession = request.session();
        final Player user = httpSession.attribute("currentUser");

        Game game = user.getGame();
        Color activeColor = game.getActiveColor();

        if(user.equals(game.getRedPlayer()) && activeColor.equals(Color.RED)){
            return gson.toJson(Message.info("true"));
        }
        else if((user.equals(game.getWhitePlayer()) && activeColor.equals(Color.WHITE))){
            return gson.toJson(Message.info("true"));
        }
        return gson.toJson(Message.info("false"));
    }
}

