package com.webcheckers.ui;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;


public class PostResignRoute implements Route{
    static final String RESIGN = "You have resigned, better luck next time.";

    private Message message;

    public PostResignRoute(TemplateEngine templateEngine) {

    }
    @Override
    public Object handle(Request request, Response response) throws Exception {
        String name = request.session().attribute(GetHomeRoute.NAME_PARAM);
        message = Message.info(RESIGN);
        String json;
        Gson gson = new GsonBuilder().create();
        json = gson.toJson( message );

        return json;
    }
}
