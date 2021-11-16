package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.model.Player;
import spark.*;

public class GetSpecStopRoute implements Route {

    private final GameCenter gameCenter;
    private TemplateEngine templateEngine;
    private final String USER = "currentUser";

    public GetSpecStopRoute(TemplateEngine templateEngine, GameCenter gameCenter)
    {
        this.gameCenter = gameCenter;
        this.templateEngine = templateEngine;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Session httpSession = request.session();
        Player user = httpSession.attribute(USER);
        user.setOutOfGame();
        response.redirect("/");
        return null;
    }
}
