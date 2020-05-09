package com.chp03tictactoe.wildtictactoe;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;


public class WildTicTacToeSpec {

    WildTicTacToe game;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        game = new WildTicTacToe();
    }

    @Test
    public void whenPieceIsPlacedInXOutsideRangeThenRuntimeException() {
        exception.expect(RuntimeException.class);
        game.play(4, 1, "O");
    }

    @Test
    public void whenPieceIsPlacedInYOutsideRangeThenRuntimeException() {
        exception.expect(RuntimeException.class);
        game.play(1, 4, "O");
    }

    @Test
    public void whenPieceNotAllowedIsPlayedThenRuntimeException() {
        exception.expect(RuntimeException.class);
        game.play(1, 1, "E");
    }

    @Test
    public void whenPieceIsPlayedInOccupiedPositionThenRuntimeException() {
        game.play(1, 1, "X");
        exception.expect(RuntimeException.class);
        game.play(1, 1, "O");
    }

    @Test
    public void givenPlayer1StartsWhenPlaysThenNextPlayerIs2(){
        game.play(1, 1, "X");
        String nextPlayer = game.nextPlayer();
        assertEquals("Player 2", nextPlayer);
    }

    @Test
    public void givenPlayer1StartsWhenPlayer2PlaysThenGameIsNotOver(){
        game.play(1, 1, "X");
        String result = game.play(2, 2, "O");
        assertEquals("Game is not over", result);
    }

    @Test
    public void whenPlayAndHorizontalLineFillThenCurrentPlayerWins(){
        game.play(1,1, "X");
        game.play(2,1, "O");
        game.play(1,2, "X");
        game.play(2,2, "O");
        String result = game.play(1,3, "X");
        assertEquals("Player 1 is the winner", result);
    }

    @Test
    public void whenPlayAndVerticalLineFillThenCurrentPlayerWins(){
        game.play(1,2, "X");
        game.play(1,1, "O");
        game.play(1,3, "X");
        game.play(2,1, "O");
        game.play(2,2, "X");
        String result = game.play(3,1, "O");
        assertEquals("Player 2 is the winner", result);
    }

    @Test
    public void whenPlayAndMainDiagonalFillThenCurrentPlayersWins(){
        game.play(1,1, "X");
        game.play(1,2, "O");
        game.play(2,2, "X");
        game.play(2,1, "O");
        String result = game.play(3,3, "X");
        assertEquals("Player 1 is the winner", result);
    }

    @Test
    public void whenPlayAndSecondaryDiagonalFillThenCurrentPlayersWins(){
        game.play(1,3, "X");
        game.play(1,1, "O");
        game.play(2,2, "X");
        game.play(1,2, "O");
        String result = game.play(3,1, "X");
        assertEquals("Player 1 is the winner", result);
    }

    @Test
    public void whenLastSpotIsOccupiedAndNoLineFillThenDraw(){
        game.play(1,1, "X");
        game.play(1,2, "O");
        game.play(1,3, "X");
        game.play(2,1, "O");
        game.play(2,2, "X");
        game.play(2,3, "O");
        game.play(3,1, "O");
        game.play(3,2, "X");
        String result = game.play(3,3, "O");
        assertEquals("The result is a draw", result);
    }



}
