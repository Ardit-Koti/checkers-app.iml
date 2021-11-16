package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

public class GetSpecStopRoute implements Route {

    private final GameCenter gameCenter;
    private TemplateEngine templateEngine;

    public GetSpecStopRoute(TemplateEngine templateEngine, GameCenter gameCenter)
    {
        this.gameCenter = gameCenter;
        this.templateEngine = templateEngine;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        return null;
    }
}
