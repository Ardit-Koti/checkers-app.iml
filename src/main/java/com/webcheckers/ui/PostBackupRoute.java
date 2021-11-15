package com.webcheckers.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcheckers.application.GameCenter;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.model.ViewMode;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.HashMap;
import java.util.Map;

import static com.webcheckers.ui.PostSignInRoute.NAME_PARAM;

public class PostBackupRoute implements Route {
    private static final String NO_BACKUP = "No moves to backup to!";
    private static final String BACKUP = "Go back to your last valid move!";
    private static final String USER = "currentUser";
    private static final String VIEW_MODE = "viewMode";
    private GameCenter gameCenter;
    private Player redPlayer;

    private Player whitePlayer;

    private Game game;

    @Override
    public Object handle(Request request, Response response) throws Exception {
        final Session httpSession = request.session();
        String playerName = request.session().attribute(NAME_PARAM);
        final Map<String, Object> vm = new HashMap<>();
        Player user = httpSession.attribute(USER);
        vm.put(VIEW_MODE, ViewMode.PLAY);
        game = user.getGame();
        String message = game.backup();
//        Game currentGame = get current board
//
//        Boolean moveleft
//
//        if(moveleft){
//            message = new InfoMessage(BACKUP);
//        }
//        else{i
//            message = new ErrorMessage(NO_BACKUP);
//        }

        // todo get currentPlayer and check the condition to see whether there is a backup move or not. If there is one, look at backupmove data
        Gson gson = new GsonBuilder().create();
        if(message.startsWith("ERROR")){
            return gson.toJson(Message.error(message.substring(7)));
        }
        return gson.toJson(Message.info(message));

    }
}
