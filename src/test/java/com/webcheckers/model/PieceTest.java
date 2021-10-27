package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.ui.PostSignInRoute;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@Tag("Model-Tier")
class PieceTest {
    private Piece CuT;

    @Test
    public void testGetType(){
        CuT = new Piece(Piece.type.KING, Piece.color.Red);
        assertEquals(Piece.type.KING, CuT.getType());
        CuT = new Piece(Piece.type.REGULAR, Piece.color.Red);
        assertEquals(Piece.type.REGULAR, CuT.getType());
    }

    @Test
    public void testGetColor(){
        CuT = new Piece(Piece.type.KING, Piece.color.Red);
        assertEquals(Piece.color.Red, CuT.getColor());
        CuT = new Piece(Piece.type.KING, Piece.color.White);
        assertEquals(Piece.color.White, CuT.getColor());
    }


}