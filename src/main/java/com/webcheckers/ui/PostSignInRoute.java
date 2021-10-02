package com.webcheckers.ui;

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

    private final String VIEW_NAME = "signin.ftl";
    private final TemplateEngine templateEngine;

    PostSignInRoute(TemplateEngine templateEngine){

        Objects.requireNonNull(templateEngine, "templateEngine must not be null");

        this.templateEngine = templateEngine;
    }


    @Override
    public String handle(Request request, Response response) {
        final Session httpSession = request.session();

        final Map<String, Object> vm = new HashMap<>();



        return templateEngine.render(new ModelAndView(vm,VIEW_NAME));

    }

}
