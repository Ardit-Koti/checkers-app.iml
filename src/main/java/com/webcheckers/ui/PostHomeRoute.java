package com.webcheckers.ui;

import com.webcheckers.util.PlayerLobby;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;
import spark.TemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static spark.Spark.halt;

public class PostHomeRoute implements Route{

    private final String VIEW_NAME = "home.ftl";
    private final TemplateEngine templateEngine;
    private final String NAME_PARAM = "name";
    private final PlayerLobby pLobby;

    PostHomeRoute(TemplateEngine templateEngine, PlayerLobby plobby){

        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        this.pLobby = plobby;
        this.templateEngine = templateEngine;
    }

    @Override
    public String handle(Request request, Response response) {
        final Session httpSession = request.session();

        final Map<String, Object> vm = new HashMap<>();
        final String playerName = request.queryParams(NAME_PARAM);
        pLobby.checkAndAddName(playerName);
        

        return templateEngine.render(new ModelAndView(vm,VIEW_NAME));

    }


}
