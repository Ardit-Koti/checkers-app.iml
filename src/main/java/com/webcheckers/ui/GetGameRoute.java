package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


import com.google.gson.Gson;

import com.webcheckers.application.GameCenter;

import com.webcheckers.model.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;
import spark.Session;

import com.webcheckers.util.Message;


public class GetGameRoute implements Route{

    static final String VIEW_NAME = "game.ftl";

    private final TemplateEngine templateEngine;


    private static final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers.");

    private final String ACTIVE = "activeColor";

    static final String NAME_PARAM = "name";

    private final String VIEW_MODE = "viewMode";

    private final String USER = "currentUser";

    private final String RED_PLAYER = "redPlayer";

    private final String WHITE_PLAYER = "whitePlayer";

    private final String BOARD = "board";

    private final String CHOSEN_PLAYER = "challenge";
    private final GameCenter gameCenter;
    private final PlayerLobby pLobby;

    private Gson gson;
    private Game game;

    /**
     * The constructor for the {@code GET /signin} route handler.
     *
     * @param templateEngine
     *    The {@link TemplateEngine} used for rendering page HTML.
     */

    GetGameRoute(final TemplateEngine templateEngine, GameCenter gameCenter, Gson gson){


        Objects.requireNonNull(templateEngine, "templateEngine must not be null");

        this.templateEngine = templateEngine;

        this.gson = gson;

        this.gameCenter = gameCenter;
        this.pLobby = gameCenter.getPlayerLobby();

    }

    @Override
    public String handle(Request request, Response response) {
        final Session httpSession = request.session();
        final Map<String, Object> vm = new HashMap<>();
        httpSession.attribute(NAME_PARAM, NAME_PARAM);
        Player youPlayer = httpSession.attribute(USER); // originally "YOU"

        if(!youPlayer.isInGame()) { // handles creating a new game between two players



            final String opponentPlayerName = request.queryParams(CHOSEN_PLAYER);
            Player opponentPlayer = gameCenter.getPlayerLobby().getPlayer(opponentPlayerName);

            if(opponentPlayer.isInGame())
            {
                vm.put("message", new Message("Player already in Game", Message.Type.ERROR));
                response.redirect("/");
                return null;
            }

            game = new Game(youPlayer, opponentPlayer);
            game.setActiveColor(Color.RED);
            youPlayer.setInGame();
            youPlayer.setGame(game);
            youPlayer.setColor(Color.RED);
            opponentPlayer.setGame(game);
            opponentPlayer.setInGame();
            opponentPlayer.setColor(Color.WHITE);

        }
        else{
            if (youPlayer.getGame() == null) { // if player resigned
                String playerName = httpSession.attribute("playerName");
                Message message = Message.info("Welcome " + playerName + " to the world of online checkers");
                vm.put("name", NAME_PARAM);
                vm.put("currentUser",httpSession.attribute("currentUser"));
                vm.put("message", message);
                vm.put("title", "Welcome!");
                vm.put("players", gameCenter.getPlayerLobby().getNamesInUse());
                vm.put("playerName", playerName);
                youPlayer.leaveGame();
                return templateEngine.render(new ModelAndView(vm , "home.ftl"));
            }
            game = youPlayer.getGame();
        }

        vm.put("title", "Welcome!");
        vm.put(USER, youPlayer);
        vm.put(VIEW_MODE, ViewMode.PLAY);
        vm.put(BOARD, game.getGameBoard());
        vm.put(RED_PLAYER, game.getRedPlayer());
        vm.put(WHITE_PLAYER, game.getWhitePlayer());
        vm.put(ACTIVE, game.getActiveColor());
        game.isOver();
        if (game.getWinner() != null){
            final Map<String, Object> modeOptions = new HashMap<>(2);
            modeOptions.put("isGameOver", true);
            modeOptions.put("gameOverMessage", String.format("Congratulation, %s won!", game.getWinPlayer().getName()));
            vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
            youPlayer.setGame(null);
            youPlayer.leaveGame();
        }
        return templateEngine.render(new ModelAndView(vm, VIEW_NAME));



    }

}
