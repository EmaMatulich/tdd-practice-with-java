package com.chp03tictactoe;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

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

    @Test
    public void givenFirstTurnWhenNextPlayerThenX(){
        assertEquals('X', game.nextPlayer());
    }

    @Test
    public void givenLastTurnWasXWhenNextPlayerThenO(){
        int x = 1;
        int y = 1;
        game.play(x, y);
        assertEquals('O', game.nextPlayer());
    }

    @Test
    public void whenPlayThenNoWinner(){
        int x = 1;
        int y = 1;
        String actual = game.play(x, y);
        assertEquals("No winner", actual);
    }

    @Test
    public void whenPlayAndWholeHorizontalLineThenWinner(){
        game.play(1, 1); //X plays
        game.play(2,1); //O plays
        game.play(1, 2); //X plays
        game.play(2,2); //O plays
        String actual = game.play(1, 3); //X plays
        assertEquals("X is the winner", actual);
    }


}
