package com.webcheckers.ui;

import com.webcheckers.model.Player;
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


/**
 * post sign in route is a class A player posts sign in to the server
 */
public class PostSignInRoute implements Route{

    private Message NAMESTATUS;
    private final String VIEW_NAME = "signin.ftl";
    private final TemplateEngine templateEngine;
    static final String NAME_PARAM = "name";
    private final PlayerLobby pLobby;
    private final String PLAYERS = "players";

    /**
     * Constructor takes in templateEngine and plobby where
     * @param templateEngine is how the page is rendered  and
     * @param plobby the lobby of players
     */
    PostSignInRoute(TemplateEngine templateEngine, PlayerLobby plobby){
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        this.pLobby = plobby;
        this.templateEngine = templateEngine;
    }

    /**
     * handle method gets called when sign in
     * @param request
     * @param response
     * @return
     */
    @Override
    public String handle(Request request, Response response) { //called again for each sign in
        final Session httpSession = request.session(); // creates HTTP session
        final Map<String, Object> vm = new HashMap<>(); //create view model hashmap that will be sent to the HTML
        final String playerName = request.queryParams(NAME_PARAM);
        // when there is a post, info is being sent to server. When you click sign in, a username is entered,
        // playername is getting the string from the sign in form

        String status = pLobby.checkAndAddName(playerName); // status is if the player is successfully joined


        if (status.equals("Success")){ //based of p lobby, if a player successfully entered the lobby
            Player user = new Player(playerName); //todo ask team about this, why do we have 2 players
            httpSession.attribute("currentUser",user); // http session represents a new user
            httpSession.attribute("playerName",playerName);
            vm.put("currentUser",user); // put the current user into the view model


            NAMESTATUS = Message.info("Welcome " + playerName + " to the world of online checkers");
            vm.put("message", NAMESTATUS);
            vm.put("title", "Welcome!");
            vm.put(PLAYERS, pLobby.NamesInUse);

            return templateEngine.render(new ModelAndView(vm,"home.ftl"));
        }



        else { // if a player does NOT successfully enter the lobby
            if (status.equals("Taken")) //if the name submitted is taken
                NAMESTATUS = Message.error("That name is taken! Please input another!");

            if (status.equals("Invalid"))
                NAMESTATUS = Message.error("That name is invalid! Please input a valid name!");

            vm.put("message", NAMESTATUS);

            return templateEngine.render(new ModelAndView(vm, "signin.ftl"));
        }
    }

}
