package com.chp03tictactoe;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TicTacToeSpec {

    TicTacToe game;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup(){
        game = new TicTacToe();
    }

    @Test
    public void whenPieceIsPlacedInXAxisOutOfRangeThenRuntimeExceptionIsThrown(){

        int x = 4;
        int y = 2;
        String piece = "X";

        exception.expect(RuntimeException.class);

        game.placePiece(piece, x, y);

    }

    @Test
    public void whenPieceIsPlacedInYAxisOutOfRangeThenRuntimeExceptionIsThrown(){
        int x = 2;
        int y = 4;
        String piece = "O";

        exception.expect(RuntimeException.class);

        game.placePiece(piece, x, y);
    }

    @Test
    public void whenPieceIsPlacedInAnOccupiedPositionThenRuntimeExceptionIsThrown(){

        int x = 2;
        int y= 2;
        String piece1 = "X";

        game.placePiece(piece1, x, y);

        String piece2 = "O";

        exception.expect(RuntimeException.class);
        game.placePiece(piece2, x, y);
    }


}
