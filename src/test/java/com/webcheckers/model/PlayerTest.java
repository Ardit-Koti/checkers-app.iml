package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

@Tag("Model-Tier")
public class PlayerTest {

    /**
     * Test the player constructor with name param.
     */
    @Test
    public void constructTest() {
        Player testPlayer = new Player("Charlie");

        assertEquals(testPlayer.name(), "Charlie");
    }

    /**
     * Test the basic player constructor without name param.
     */
    @Test
    public void nullConstructTest() {
        Player testPlayer = new Player();

        assertNull(testPlayer.name());
    }

    /**
     * Test the setName function.
     */
    @Test
    public void setNameTest() {
        Player testPlayer = new Player();
        testPlayer.setName("Tyler");

        assertEquals(testPlayer.name(), "Tyler");
    }

    /**
     * Test multiple calls of the setName function.
     */
    @Test
    public void multSetNameTest() {
        Player testPlayer = new Player();
        testPlayer.setName("Keith");
        testPlayer.setName("Lily");

        assertEquals(testPlayer.name(), "Lily");
    }
}