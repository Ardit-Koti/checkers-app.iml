package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

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
        vm.put(VIEW_MODE, ViewMode.PLAY);
        Player YOU = httpSession.attribute(USER);
        final String player_name = request.queryParams(CHOSEN_PLAYER);
        System.out.println(YOU.getName());
        Player Opps = pLobby.getPlayer(player_name);
        System.out.println(httpSession.attributes());
        //If User calling this route is in a Game, gets game info and renders it
        if(Opps == null) {
            if (YOU.getGame().getWhitePlayer() != null) {
                vm.put("title", "Welcome!");
                vm.put(USER, YOU);
                vm.put(BOARD, YOU.getGame().getGameBoard());
                vm.put(RED_PLAYER, YOU.getGame().getRedPlayer());
                vm.put(WHITE_PLAYER, YOU.getGame().getWhitePlayer());
                vm.put(ACTIVE, YOU.getGame().getColor());
                System.out.println("putting player in Game");
                return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
            }
        }
        else {
            //If user is looking to start a game
            if(Opps.isInGame())
            {
              vm.put("message", new Message("Player already in Game", Message.Type.ERROR));
              response.redirect("/");
              return null;
            }
            YOU.setColor(Player.Color.RED);
            Opps.setColor(Player.Color.WHITE);
            vm.put("title", "Welcome!");
            Game newGame = new Game(YOU, Opps);
            YOU.setGame(newGame);
            YOU.setInGame();
            Opps.setGame(newGame);
            Opps.setInGame();
            vm.put(USER, YOU);
            vm.put(BOARD, newGame.getGameBoard());
            vm.put(RED_PLAYER, YOU);
            vm.put(WHITE_PLAYER, Opps);
            vm.put(ACTIVE, ActiveColor.RED);
            newGame.setActiveColor(ActiveColor.RED);
            System.out.println("Game Started");
        }
        return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
    }

}
