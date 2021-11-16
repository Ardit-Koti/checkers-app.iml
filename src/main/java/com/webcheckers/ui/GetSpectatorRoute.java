package com.webcheckers.ui;

import com.google.gson.Gson;
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
    private final Gson gson;
    private final PlayerLobby pLobby;
    private Game game;

    public GetSpectatorRoute(TemplateEngine templateEngine, GameCenter gameCenter, Gson gson) {
        this.gameCenter = gameCenter;
        this.templateEngine = templateEngine;
        this.gson = gson;
        this.pLobby = gameCenter.getPlayerLobby();
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        final Session httpSession = request.session();
        final Map<String, Object> vm = new HashMap<>();
        Player user = httpSession.attribute("currentUser");

        if (user.getGame() == null) {
            String SpecTarName = request.queryParams(WHO_YOU_ARE_WATCHING);
            Player SpecTar = gameCenter.getPlayerLobby().getPlayer(SpecTarName);
            Integer gameId = SpecTar.getGame().getGameId();
            System.out.println(gameId);
            game = gameCenter.getGame(gameId);
            user.setGame(game);
            user.setInGame();
        }

        game = user.getGame();



        if (game == null){
            return gson.toJson(Message.info("The game has concluded please leave"));
        }

        vm.put(USER, user);
        vm.put(VIEW, ViewMode.SPECTATOR);
        vm.put(RED_PLAYER, game.getRedPlayer());
        vm.put(WHITE_PLAYER, game.getWhitePlayer());
        vm.put(BOARD, game.getGameBoard());
        vm.put(ACTIVE, game.getActiveColor());
        Message message = new Message("You started spectating", Message.Type.INFO);
        vm.put(MESSAGE, message);
        vm.put("title", "Welcome!");
        vm.put(GAME_ID, game.getGameId());

        if (game.getWinner() != null){
            final Map<String, Object> modeOptions = new HashMap<>(2);
            modeOptions.put("isGameOver", true);
            modeOptions.put("gameOverMessage", String.format("Congratulation, %s won!", game.getWinPlayer().getName()));
            vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
            user.setGame(null);
            user.leaveGame();
        }

        return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
    }
}
