package com.webcheckers.ui;

import com.webcheckers.model.Player;
import com.webcheckers.model.PlayerLobby;
import com.webcheckers.util.Message;
import spark.*;
import java.util.HashMap;
import java.util.Map;

public class PostSignOutRoute implements Route {
    private final int SESSION_TIMEOUT_PERIOD = 600;
    static final String PLAYER_LOBBY = "pLobby";
    private final String NUM_PLAYERS = "playernum";
    private static final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers.");
    private final String VIEW_NAME = "home.ftl";
    private final TemplateEngine templateEngine;
    private final PlayerLobby pLobby;
    private final String player = "currentUser";

    public PostSignOutRoute(TemplateEngine templateEngine, PlayerLobby pLobby) {
        this.templateEngine = templateEngine;
        this.pLobby = pLobby;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        final Session httpSession = request.session();
        final Map<String, Object> vm = new HashMap<>();
        final Player p = httpSession.attribute(player);
        final String playerName = p.getName();
        System.out.println(playerName);
        pLobby.removeName(playerName);
        httpSession.maxInactiveInterval(SESSION_TIMEOUT_PERIOD);
        httpSession.attribute("currentUser", null);
        httpSession.attribute(PLAYER_LOBBY, pLobby);
        vm.put("title", "Welcome!");
        vm.put("message", WELCOME_MSG);
        vm.put(NUM_PLAYERS, pLobby.numberOfPlayers());
        response.redirect("/");
        return null;
    }
}
