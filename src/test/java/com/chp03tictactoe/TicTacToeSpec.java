package com.chp03tictactoe;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TicTacToeSpec {

    static TicTacToe game;

    @Rule
    public ExpectedException runtime = ExpectedException.none();

    @BeforeClass
    public static void setup(){
        game = new TicTacToe();
    }

    @Test
    public void xAxisTooLargeShouldThrowRuntimeException(){

        int x = 4;
        int y = 2;
        String piece = "X";

        runtime.expect(RuntimeException.class);

        game.placePiece(piece, x, y);

    }
}
