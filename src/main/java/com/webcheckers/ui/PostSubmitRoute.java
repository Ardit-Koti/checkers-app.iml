package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.application.GameCenter;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;

public class PostSubmitRoute implements Route{

    private final TemplateEngine templateEngine;
    private final Gson gson;
    private final GameCenter gameCenter;

    public PostSubmitRoute(TemplateEngine templateEngine, GameCenter gameCenter, Gson gson){
        this.templateEngine = templateEngine;
        this.gson = gson;
        this.gameCenter = gameCenter;
    }


    @Override
    public Object handle(Request request, Response response) throws Exception {
        final Session httpSession = request.session();
        final Player player = httpSession.attribute("currentUser");
        Game game = player.getGame();


        String message = game.submitMove();
        if(message.startsWith("ERROR")){
            return gson.toJson(Message.error(message.substring(7)));
        }
        return gson.toJson(Message.info(message));
    }
}
