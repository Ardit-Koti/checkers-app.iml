package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.*;
import com.webcheckers.util.Message;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;
import spark.Session;

public class PostValidateMove implements Route{

    private final TemplateEngine templateEngine;
    private final PlayerLobby pLobby;

    public PostValidateMove(TemplateEngine templateEngine, PlayerLobby pLobby) {
        this.templateEngine = templateEngine;
        this.pLobby = pLobby;
    }



    @Override
    public Object handle(Request request, Response response) throws Exception {
        Gson gson = new Gson(); //todo make gson object global using webserver, similar to template engine
        Move move = gson.fromJson(request.queryParams("actionData"), Move.class);

        System.out.println("Move info:: \n    " +
                "start: (" + move.getStart().getCell()+ ","+ move.getStart().getRow() + ")"+"\n    " +
                "end:   ("+move.getEnd().getCell()+ ","+ move.getEnd().getRow() + ")");

        //todo implement checking a move
        return gson.toJson(Message.info(" ~test~ "));
    }
}
