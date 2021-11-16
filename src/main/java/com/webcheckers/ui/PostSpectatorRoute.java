package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

public class PostSpectatorRoute implements Route {
    private final GameCenter gameCenter;
    private TemplateEngine templateEngine;

    public PostSpectatorRoute(TemplateEngine templateEngine, GameCenter gameCenter)
    {
        this.templateEngine = templateEngine;
        this.gameCenter = gameCenter;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Player spectator = request.session().attribute("Player");
//        gameCenter.removeSpectator(spectator);

        request.session().attribute("message", new Message(String.format("Game is over."), Message.Type.INFO));
        return null;
    }
}
