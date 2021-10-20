package com.webcheckers.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerLobbyTest {

    @Test
    void checkAndAddName() {

        PlayerLobby Pl = new PlayerLobby();


        //test valid
        assertEquals("Success",  Pl.checkAndAddName("Joe"));
        // test invalid
        assertEquals("Invalid",  Pl.checkAndAddName("^%(#"));
        // test taken
        assertEquals("Taken",  Pl.checkAndAddName("Joe"));
        // test number of players
        assertEquals(1,  Pl.numberOfPlayers());
        // test iterator
        // TODO: 10/19/21 test iterator  
        //assertEquals(1,  Pl.numberOfPlayers());
    }

    }
}