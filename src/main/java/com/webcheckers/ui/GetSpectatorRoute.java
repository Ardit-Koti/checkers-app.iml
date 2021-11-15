package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.model.Player;
import com.webcheckers.model.PlayerLobby;
import spark.Request;
import spark.Response;
import spark.Route;

public class GetSpectatorRoute implements Route {
    private final GameCenter gameCenter;
    private final PlayerLobby playerLobby;

    public GetSpectatorRoute(GameCenter gameCenter, PlayerLobby playerLobby) {
        this.gameCenter = gameCenter;
        this.playerLobby = playerLobby;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Player spectator = request.session().attribute("Player");
        Player gamePlayer = playerLobby.getPlayer(request.queryParams("redPlayer"));

        //gamecenter.addspectator(spectator, gameplayer);

        response.redirect(WebServer.GAME_URL);

        return null;
    }
}
