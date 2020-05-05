package com.chp03tictactoe;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TicTacToeSpec {

    private TicTacToe game;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public final void setup(){
        game = new TicTacToe();
    }

    /**
     * Test name recomended - BDD scenarios
     *  given/when/then
     *  given--> preconditions
     *  when--> actions
     *  then-->expected outcome
     *  if a test does not have preconditions (set up on @before or @beforeclass)
     *  given can be ommited
     */

    @Test
    public void whenPieceIsPlacedInXPositionOutOfRangeThenRuntimeException(){

        int x = 4;
        int y = 2;

        exception.expect(RuntimeException.class);

        game.play(x, y);
    }

    @Test
    public void whenPieceIsPlacedInYPositionOutOfRangeThenRuntimeException(){

        int x = 2;
        int y = 4;

        exception.expect(RuntimeException.class);

        game.play(x, y);
    }

    @Test
    public void whenPlacingAPieceInOccupiedPositionThenRuntimeException(){
        int x = 2;
        int y = 2;

        game.play(x, y);

        exception.expect(RuntimeException.class);

        game.play(x, y);
    }

}
