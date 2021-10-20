package com.webcheckers.ui;

import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import com.webcheckers.ui.GetSignInRoute;
import com.webcheckers.ui.PostSignInRoute;
import spark.HaltException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateEngine;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.webcheckers.model.PlayerLobby;
import spark.*;
import com.webcheckers.ui.WebServer;



@Tag("UI-Tier")
class PostSignInRouteTest {
    private PostSignInRoute CuT;
    private Request request;
    private Session session;
    private Response response;
    private TemplateEngine engine;
    private PlayerLobby plobby;

    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);

        //
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);

        plobby = mock(PlayerLobby.class);

        CuT = new PostSignInRoute(engine, plobby);
    }


        // test a valid player sign in
        @Test
        public void test1(){

            when(request.session()).thenReturn(session); //ask about this

            CuT.handle(request, response);
            // look for every method that is called and mock it

            String s = " "; //not testing, just running method
            when(request.queryParams(any(String.class))).thenReturn(s);//s is the player name that is returned
            when(plobby.checkAndAddName(s)).thenReturn("Success"); //checks the string s from above and sets it to return sucess
            //HTTP session . attribute is just storing, so we do not need to test

            final TemplateEngineTester testHelper = new TemplateEngineTester();
            testHelper.assertViewModelExists();

            testHelper.assertViewModelAttribute("message",  Message.info("Welcome " + s + " to the world of online checkers").);
            //next vm.puts

            //return statement does not need to bed tested, but we could check that it is not null

            // do we need to test vm and stuff up there?

        //pass in player lobby
        //when check and add names is called, return success



        }

        // test a failed sign in
        @Test
        public void test2() {

        }

        }
}