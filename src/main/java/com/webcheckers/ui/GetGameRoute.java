package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.model.Player;
import com.webcheckers.model.PlayerLobby;
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

    static final String NAME_PARAM = "name";
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

        System.out.println("Game Started");

        return templateEngine.render(new ModelAndView(vm,VIEW_NAME));

    }

}
