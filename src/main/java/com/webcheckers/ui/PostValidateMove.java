package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.application.MoveValidator;
import com.webcheckers.model.*;
import com.webcheckers.util.Message;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;
import spark.Session;

import java.util.HashMap;
import java.util.Map;

public class PostValidateMove implements Route{


    static final String NAME_PARAM = "name";
    private final String VIEW_MODE = "viewMode";
    private final String USER = "currentUser";
    private final String CHOSEN_PLAYER = "challenge";

    private final TemplateEngine templateEngine;
    private final PlayerLobby pLobby;

    public PostValidateMove(TemplateEngine templateEngine, PlayerLobby pLobby) {
        this.templateEngine = templateEngine;
        this.pLobby = pLobby;
    }



    @Override
    public Object handle(Request request, Response response) throws Exception {


        // getting session information and the proper game
        final Session httpSession = request.session();
        final Map<String, Object> vm = new HashMap<>();
        httpSession.attribute(GetHomeRoute.PLAYER_LOBBY, pLobby);
        httpSession.attribute(NAME_PARAM, NAME_PARAM);
        vm.put(VIEW_MODE, ViewMode.PLAY); // Need to pass in 3 players

        Player youPlayer = httpSession.attribute(USER); // originally "YOU"
        final String opponentPlayerName = request.queryParams(CHOSEN_PLAYER);


        //todo




        Gson gson = new Gson(); //todo make gson object global using webserver, similar to template engine
        Move move = gson.fromJson(request.queryParams("actionData"), Move.class);

        //go player -> game


        System.out.println("Move info:: \n    " +
                "start: (" + move.getStartPos().getCell()+ ","+ move.getStartPos().getRow() + ")"+"\n    " +
                "end:   ("+move.getEndPos().getCell()+ ","+ move.getEndPos().getRow() + ")");

        //todo implement checking a move
        if (MoveValidator.isOneDiagonal(move)) // is a one diagonal move
            return gson.toJson(Message.info(" ~test~ valid"));
        else
            return gson.toJson(Message.error(" ~test~ not valid"));
    }
}
