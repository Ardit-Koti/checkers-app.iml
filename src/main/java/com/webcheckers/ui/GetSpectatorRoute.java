package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.model.PlayerLobby;
import com.webcheckers.model.ViewMode;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;

public class GetSpectatorRoute implements Route {
    private final String VIEW_NAME = "game.ftl";
    private final String WHO_YOU_ARE_WATCHING = "spectate";
    private final String USER = "currentUser";
    private final String VIEW = "viewMode";
    private final String RED_PLAYER = "redPlayer";
    private final String WHITE_PLAYER = "whitePlayer";
    private final String ACTIVE = "activeColor";
    private final String BOARD = "board";
    private final String MESSAGE = "message";
    private final String GAME_ID = "gameID";
    private final GameCenter gameCenter;
    private final TemplateEngine templateEngine;

    public GetSpectatorRoute(TemplateEngine templateEngine, GameCenter gameCenter) {
        this.gameCenter = gameCenter;
        this.templateEngine = templateEngine;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        final Session httpSession = request.session();
        final Map<String, Object> vm = new HashMap<>();
        Player user = httpSession.attribute("currentUser");
        vm.put(USER, user);
        vm.put(VIEW, ViewMode.SPECTATOR);
        PlayerLobby pLobby = gameCenter.getPlayerLobby();
        String player_name = request.queryParams(WHO_YOU_ARE_WATCHING);
        System.out.println(player_name);
        Player person_playing = gameCenter.getPlayerLobby().getPlayer(player_name);
        Game spec_game = person_playing.getGame();
        vm.put(RED_PLAYER, spec_game.getRedPlayer());
        vm.put(WHITE_PLAYER, spec_game.getWhitePlayer());
        vm.put(BOARD, spec_game.getGameBoard());
        vm.put(ACTIVE, spec_game.getActiveColor());
        Message message = new Message("Spectating " + player_name, Message.Type.INFO);
        vm.put(MESSAGE, message);
        vm.put("title", "Welcome!");
        vm.put(GAME_ID, spec_game.getGameId());
        Player spectator = request.session().attribute("Player");
        user.setInGame();
        Player gamePlayer = pLobby.getPlayer(request.queryParams("redPlayer"));

        return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
    }
}
