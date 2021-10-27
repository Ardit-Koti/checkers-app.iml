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
        final Player player = httpSession.attribute(USER);
        vm.put("title", "Welcome!");
        vm.put(USER, player);
        //Temporary just to see render
        //---
        Game newGame = new Game(new Player("red", Player.Color.RED),
                new Player("white", Player.Color.WHITE));
        //---
        vm.put(BOARD, newGame.getGameBoard());
        vm.put(RED_PLAYER, newGame.getRedPlayer());
        vm.put(WHITE_PLAYER, newGame.getWhitePlayer());
        vm.put(ACTIVE, ActiveColor.WHITE);
        System.out.println("Game Started");
        return templateEngine.render(new ModelAndView(vm,VIEW_NAME));

    }

}
