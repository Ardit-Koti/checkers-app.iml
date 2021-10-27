package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@Tag("Model-Tier")
public class PlayerLobbyTest {

    @Test
    public void constuctionTest() {
        PlayerLobby testLobby = new PlayerLobby();

        assertEquals(0,testLobby.numberOfPlayers());
    }

    @Test
    public void checkAndAddNameTest(){
        PlayerLobby testLobby = new PlayerLobby();
        String invalid = testLobby.checkAndAddName("I will fail");
        String valid = testLobby.checkAndAddName("Billy");
        String taken = testLobby.checkAndAddName("Billy");

        assertEquals("Invalid", invalid);
        assertEquals("Success", valid);
        assertEquals("Taken",taken);

        assertEquals(2,testLobby.numberOfPlayers());
    }

    @Test
    public void iteratorTest(){
        PlayerLobby testLobby = new PlayerLobby();

        List<Player> testPlayers = new ArrayList<>();
        testPlayers.add(new Player("Noah"));
        testPlayers.add(new Player("Katie"));

        testLobby.checkAndAddName("Noah");
        testLobby.checkAndAddName("Katie");

        assertEquals(testPlayers.iterator(), testLobby.iterator());
    }
}