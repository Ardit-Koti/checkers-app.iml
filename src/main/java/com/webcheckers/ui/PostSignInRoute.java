package com.webcheckers.ui;

import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
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




public class PostSignInRoute implements Route{

    private Message NAMESTATUS;
    private final String VIEW_NAME = "signin.ftl";
    private final TemplateEngine templateEngine;
    private final String NAME_PARAM = "name";
    private final PlayerLobby pLobby;

    PostSignInRoute(TemplateEngine templateEngine, PlayerLobby plobby){

        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        this.pLobby = plobby;
        this.templateEngine = templateEngine;
    }


    @Override
    public String handle(Request request, Response response) {
        final Session httpSession = request.session();

        final Map<String, Object> vm = new HashMap<>();
        final String playerName = request.queryParams(NAME_PARAM);
        String status = pLobby.checkAndAddName(playerName);
        if (status.equals("Success")){
            Player user = new Player(playerName);
            httpSession.attribute("currentUser",user);
            httpSession.attribute("playerName",playerName);
            vm.put("currentUser",user);


            NAMESTATUS = Message.info("Welcome " + playerName + " to the world of online checkers");
            vm.put("message", NAMESTATUS);
            vm.put("title", "Welcome!");
            return templateEngine.render(new ModelAndView(vm,"home.ftl"));
        }
        else {
            if (status.equals("Taken")){NAMESTATUS = Message.error("That name is taken! Please input another!");}
            if (status.equals("Invalid")){NAMESTATUS = Message.error("That name is invalid! Please input a valid name!");}
            vm.put("message", NAMESTATUS);
            return templateEngine.render(new ModelAndView(vm, "signin.ftl"));
        }
    }

}
