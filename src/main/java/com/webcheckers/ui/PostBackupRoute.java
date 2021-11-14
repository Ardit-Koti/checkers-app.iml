package com.webcheckers.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;

import static com.webcheckers.ui.PostSignInRoute.NAME_PARAM;

public class PostBackupRoute implements Route {
    private static final String NO_BACKUP = "No moves to backup to!";
    private static final String BACKUP = "Go back to your last valid move!";
    private Player redPlayer;

    private Player whitePlayer;

    private Message message;
    private Game game;

    @Override
    public Object handle(Request request, Response response) throws Exception {
        String playerName = request.session().attribute(NAME_PARAM);
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
        String json;
        Gson gson = new GsonBuilder().create();
        json = gson.toJson( message );

        return json;
    }
}
