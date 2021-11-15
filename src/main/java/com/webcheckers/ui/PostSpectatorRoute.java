package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;

public class PostSpectatorRoute implements Route {
    private final GameCenter gameCenter;

    public PostSpectatorRoute(GameCenter gameCenter) {
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
