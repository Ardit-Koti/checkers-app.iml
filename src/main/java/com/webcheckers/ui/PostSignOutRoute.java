package com.webcheckers.ui;

import com.webcheckers.model.PlayerLobby;
import spark.*;

import java.util.HashMap;
import java.util.Map;

public class PostSignOutRoute implements Route {
    private final String VIEW_NAME = "home.ftl";
    private final TemplateEngine templateEngine;
    private final PlayerLobby pLobby;

    public PostSignOutRoute(TemplateEngine templateEngine, PlayerLobby pLobby) {
        this.templateEngine = templateEngine;
        this.pLobby = pLobby;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        final Session httpSession = request.session();
        final Map<String, Object> vm = new HashMap<>();
        return templateEngine.render(new ModelAndView(vm, "home.ftl"));
    }
}
