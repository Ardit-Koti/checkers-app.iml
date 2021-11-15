package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.google.gson.Gson;
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

    private final PlayerLobby pLobby;

    private static final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers.");

    private final String ACTIVE = "activeColor";

    static final String NAME_PARAM = "name";

    private final String VIEW_MODE = "viewMode";

    private final String USER = "currentUser";

    private final String RED_PLAYER = "redPlayer";

    private final String WHITE_PLAYER = "whitePlayer";

    private final String BOARD = "board";

    private final String CHOSEN_PLAYER = "challenge";

    private Gson gson;

    /**
     * The constructor for the {@code GET /signin} route handler.
     *
     * @param templateEngine
     *    The {@link TemplateEngine} used for rendering page HTML.
     */
    GetGameRoute(final TemplateEngine templateEngine, PlayerLobby pLobby, Gson gson){

        Objects.requireNonNull(templateEngine, "templateEngine must not be null");

        this.templateEngine = templateEngine;
        this.pLobby = pLobby;
        this.gson = gson;
    }

    @Override
    public String handle(Request request, Response response) {
        final Session httpSession = request.session();
        final Map<String, Object> vm = new HashMap<>();
        httpSession.attribute(GetHomeRoute.PLAYER_LOBBY, pLobby);
        httpSession.attribute(NAME_PARAM, NAME_PARAM);
        Player youPlayer = httpSession.attribute(USER); // originally "YOU"



        final String opponentPlayerName = request.queryParams(CHOSEN_PLAYER);
        Player opponentPlayer = pLobby.getPlayer(opponentPlayerName);

        //If User calling this route is in a Game, gets game info and renders it
        if(opponentPlayer == null) {
            if (youPlayer.getGame() == null){
                String playerName = httpSession.attribute("playerName");
                Message message = Message.info("Welcome " + playerName + " to the world of online checkers");
                vm.put("name", NAME_PARAM);
                vm.put("currentUser",httpSession.attribute("currentUser"));
                vm.put("message", message);
                vm.put("title", "Welcome!");
                vm.put("players", pLobby.getNamesInUse());
                vm.put("playerName", playerName);
                Player user = pLobby.getPlayer(playerName);
                user.leaveGame();
                return templateEngine.render(new ModelAndView(vm, "home.ftl"));
            }
            if (!youPlayer.getGame().getOtherPlayer(youPlayer).isInGame()) //Complicated way to check if the other player of your game is not still in your game
            {
                final Map<String, Object> modeOptions = new HashMap<>();
                modeOptions.put("isGameOver", true);
                modeOptions.put("gameOverMessage", new Message("Your opponent resigned", Message.Type.INFO));
                vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
                return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
            }


            if (youPlayer.getGame().getWhitePlayer() != null) {
                vm.put("title", "Welcome!");
                vm.put(USER, youPlayer);
                vm.put(VIEW_MODE, ViewMode.PLAY);
                vm.put(BOARD, youPlayer.getGame().getGameBoard());
                vm.put(RED_PLAYER, youPlayer.getGame().getRedPlayer());
                vm.put(WHITE_PLAYER, youPlayer.getGame().getWhitePlayer());
                vm.put(ACTIVE, youPlayer.getGame().getActiveColor());
                //System.out.println("ln 97 putting player in Game");
                return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
            }
        }
        else {
            //If user is looking to start a game
            if(opponentPlayer.isInGame())
            {
              vm.put("message", new Message("Player already in Game", Message.Type.ERROR));
              response.redirect("/");
              return null;
            }
            youPlayer.setColor(Color.RED);
            opponentPlayer.setColor(Color.WHITE);
            vm.put("title", "Welcome!");
            Game newGame = new Game(youPlayer, opponentPlayer);
            youPlayer.setGame(newGame);
            youPlayer.setInGame();
            opponentPlayer.setGame(newGame);
            opponentPlayer.setInGame();
            vm.put(VIEW_MODE, ViewMode.PLAY);
            vm.put(USER, youPlayer);
            vm.put(BOARD, newGame.getGameBoard());
            vm.put(RED_PLAYER, youPlayer);
            vm.put(WHITE_PLAYER, opponentPlayer);
            vm.put(ACTIVE, Color.RED);
            newGame.setActiveColor(Color.RED);
            //System.out.println("Game Started");
        }
        return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
    }

}
