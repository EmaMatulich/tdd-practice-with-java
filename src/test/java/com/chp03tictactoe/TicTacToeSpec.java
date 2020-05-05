package com.chp03tictactoe;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TicTacToeSpec {

    static TicTacToe game;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @BeforeClass
    public static void setup(){
        game = new TicTacToe();
    }

    @Test
    public void xAxisTooOutOfRangeThrowsRuntimeException(){

        int x = 4;
        int y = 2;
        String piece = "X";

        exception.expect(RuntimeException.class);

        game.placePiece(piece, x, y);

    }

    @Test
    public void yAxisOutOfRangeThrowsRuntimeException(){
        int x = 2;
        int y = 4;
        String piece = "O";

        exception.expect(RuntimeException.class);

        game.placePiece(piece, x, y);
    }

    @Test
    public void placeAPieceInOcupatedPositionThrowsRuntimeException(){
        int x = 2;
        int y= 2;
        String piece1 = "X";

        game.placePiece(piece1, x, y);

        String piece2 = "O";

        exception.expect(RuntimeException.class);
        game.placePiece(piece2, x, y);
    }


}
