package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.model.Game;
import com.webcheckers.util.Message;
import com.webcheckers.model.PlayerLobby;
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
    private static final Message WELCOME_MSG = Message.info("Welcome user! Please choose an activity");
    private final GameCenter gameCenter;

    PostHomeRoute(TemplateEngine templateEngine, GameCenter gameCenter){

        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        this.gameCenter = gameCenter;
        this.templateEngine = templateEngine;
    }

    @Override
    public String handle(Request request, Response response) {
        final Session httpSession = request.session();

        final Map<String, Object> vm = new HashMap<>();


        vm.put("title", "Welcome!");
        vm.put("message", WELCOME_MSG);

        return templateEngine.render(new ModelAndView(vm,VIEW_NAME));

    }


}
