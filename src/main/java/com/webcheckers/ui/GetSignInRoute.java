package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;
import spark.Session;

import com.webcheckers.util.Message;

public class GetSignInRoute implements Route{

    static final String VIEW_NAME = "signin.ftl";
    private static final Message WELCOME_MSG = Message.info("Please Login");
    private final TemplateEngine templateEngine;

    /**
     * The constructor for the {@code GET /signin} route handler.
     *
     * @param templateEngine
     *    The {@link TemplateEngine} used for rendering page HTML.
     */
    GetSignInRoute(final TemplateEngine templateEngine){

        Objects.requireNonNull(templateEngine, "templateEngine must not be null");

        this.templateEngine = templateEngine;
    }


    @Override
    public String handle(Request request, Response response) {
        final Session httpSession = request.session();

        final Map<String, Object> vm = new HashMap<>();
        if (httpSession.attribute("currentUser") != null) {
            String playerName = httpSession.attribute("playerName");
            Message message = Message.info("Welcome " + playerName + " to the world of online checkers");
            vm.put("currentUser",httpSession.attribute("currentUser"));
            vm.put("message", message);
            vm.put("title", "Welcome!");
            return templateEngine.render(new ModelAndView(vm, "home.ftl"));
        }

        vm.put("message", WELCOME_MSG);
        return templateEngine.render(new ModelAndView(vm,VIEW_NAME));

    }
}
