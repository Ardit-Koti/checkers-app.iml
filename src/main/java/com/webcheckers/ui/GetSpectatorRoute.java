package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.model.PlayerLobby;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

public class GetSpectatorRoute implements Route {
    private final GameCenter gameCenter;
    private final TemplateEngine templateEngine;

    public GetSpectatorRoute(TemplateEngine templateEngine, GameCenter gameCenter) {
        this.gameCenter = gameCenter;
        this.templateEngine = templateEngine;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        PlayerLobby pLobby = gameCenter.getPlayerLobby();
        Player spectator = request.session().attribute("Player");
        Player gamePlayer = pLobby.getPlayer(request.queryParams("redPlayer"));

        //gamecenter.addspectator(spectator, gameplayer);

        response.redirect(WebServer.GAME_URL);

        return null;
    }
}
