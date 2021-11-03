package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    /**
     * The constructor for the {@code GET /signin} route handler.
     *
     * @param templateEngine
     *    The {@link TemplateEngine} used for rendering page HTML.
     */
    GetGameRoute(final TemplateEngine templateEngine, PlayerLobby pLobby){

        Objects.requireNonNull(templateEngine, "templateEngine must not be null");

        this.templateEngine = templateEngine;
        this.pLobby = pLobby;
    }

    @Override
    public String handle(Request request, Response response) {

        //todo assign player colors based on rules "The red player moves first."


        //todo call model classes to make model board


        //todo sent java board info the game .ftl



        final Session httpSession = request.session();
        final Map<String, Object> vm = new HashMap<>();
        httpSession.attribute(GetHomeRoute.PLAYER_LOBBY, pLobby);
        httpSession.attribute(NAME_PARAM, NAME_PARAM);
        vm.put(VIEW_MODE, ViewMode.PLAY); // Need to pass in 3 players

        Player youPlayer = httpSession.attribute(USER); // originally "YOU"
        final String opponentPlayerName = request.queryParams(CHOSEN_PLAYER);

        System.out.println("ln80 Chosen Player: "+opponentPlayerName);
        System.out.println("ln81 you player: "+youPlayer.getName());

        Player opponentPlayer = pLobby.getPlayer(opponentPlayerName);
        System.out.println("ln84 httpSession.attributes(): "+ httpSession.attributes());


        //If User calling this route is in a Game, gets game info and renders it
        if(opponentPlayer == null) {
            if (youPlayer.getGame().getWhitePlayer() != null) {
                vm.put("title", "Welcome!");
                vm.put(USER, youPlayer);
                vm.put(BOARD, youPlayer.getGame().getGameBoard());
                vm.put(RED_PLAYER, youPlayer.getGame().getRedPlayer());
                vm.put(WHITE_PLAYER, youPlayer.getGame().getWhitePlayer());
                vm.put(ACTIVE, youPlayer.getGame().getColor());
                System.out.println("ln 97 putting player in Game");
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
            youPlayer.setColor(Player.Color.RED);
            opponentPlayer.setColor(Player.Color.WHITE);
            vm.put("title", "Welcome!");
            Game newGame = new Game(youPlayer, opponentPlayer);
            System.out.println("ln 113 youPlayer: "+youPlayer);
            System.out.println("ln 114 opponentPlayer: "+opponentPlayer);
            youPlayer.setGame(newGame);
            youPlayer.setInGame();
            opponentPlayer.setGame(newGame);
            opponentPlayer.setInGame();
            vm.put(USER, youPlayer);
            vm.put(BOARD, newGame.getGameBoard());
            vm.put(RED_PLAYER, youPlayer);
            vm.put(WHITE_PLAYER, opponentPlayer);
            vm.put(ACTIVE, ActiveColor.RED);
            newGame.setActiveColor(ActiveColor.RED);
            System.out.println("Game Started");
        }
        return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
    }

}
