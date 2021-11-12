package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;

public class PostSubmitRoute implements Route{

    private final TemplateEngine templateEngine;
    private final Gson gson;

    public PostSubmitRoute(TemplateEngine templateEngine, Gson gson){
        this.templateEngine = templateEngine;
        this.gson = gson;
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
