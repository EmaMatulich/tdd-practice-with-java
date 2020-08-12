package com.chp06tictactoev2;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class TicTacToeSpec {

    private TicTacToe game;
    private TicTacToeCollection collection;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public final void setup() throws UnknownHostException {
        collection = mock(TicTacToeCollection.class);
        doReturn(true).when(collection).saveMove(any(TicTacToeBean.class));
        doReturn(true).when(collection).drop();
        game = new TicTacToe(collection);
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

    @Test
    public void whenPlayAndWholeVerticalLineThenWinner(){
        game.play(1,1); //X plays
        game.play(1,2); //O plays
        game.play(2,3); //X plays
        game.play(2,2); //O plays
        game.play(3,1); //X plays
        String actual = game.play(3,2); //O plays
        assertEquals("O is the winner", actual);
    }

    @Test
    public void whenPlayAndTopBottomDiagonalLineThenWinner(){
        game.play(1, 1); //plays x
        game.play(1, 2); //plays O
        game.play(2, 2); //plays x
        game.play(2, 1); //plays o
        String actual = game.play(3, 3); //plays x
        assertEquals("X is the winner", actual);
    }

    @Test
    public void whenPlayAndBottomTopDiagonalLineThenWinner(){
        game.play(1, 1); //plays x
        game.play(1, 3); //plays O
        game.play(1, 2); //plays x
        game.play(2, 2); //plays O
        game.play(2, 1); //plays x
        String actual = game.play(3, 1); //plays O
        assertEquals("O is the winner", actual);
    }

    @Test
    public void whenAllPlacesAreFilledThenDraw(){
        game.play(1,1);
        game.play(1,2);
        game.play(1,3);
        game.play(2,1);
        game.play(2,3);
        game.play(2,2);
        game.play(3,1);
        game.play(3,3);
        String actual = game.play(3,2);
        assertEquals("The result is a draw", actual);
    }


    @Test
    public void whenInstantiatedThenSetCollection(){
        assertNotNull(game.getTicTacToeCollection());
    }

    @Test
    public void whenInstantiatedThenDropInvoked() throws UnknownHostException {
        collection = mock(TicTacToeCollection.class);
        doReturn(true).when(collection).drop();
        new TicTacToe(collection);
        verify(collection, times(1)).drop();
    }

    @Test
    public void whenInstantiatedAndDropReturnsFalseThenRuntimeException() throws UnknownHostException {
        collection = mock(TicTacToeCollection.class);
        doReturn(false).when(collection).drop();
        exception.expect(RuntimeException.class);
        new TicTacToe(collection);
    }

    @Test
    public void whenPlayThenSaveMoveIsInvoked(){
        TicTacToeBean move = new TicTacToeBean(1, 1, 3, 'X');
        game.play(move.getX(), move.getY());
        verify(collection, times(1)).saveMove(move);
    }


    @Test
    public void whenPlayAndSaveMoveReturnsFalseThenThrowException(){
        doReturn(false).when(collection).saveMove(any(TicTacToeBean.class));
        TicTacToeBean bean = new TicTacToeBean(1,1,3,'X');
        exception.expect(RuntimeException.class);
        game.play(bean.getX(), bean.getY());
    }

    @Test
    public void whenPLayInvokedMultipleTimesThenTurnIncrease(){
        TicTacToeBean firstMove = new TicTacToeBean(1,1,1,'X');
        game.play(firstMove.getX(), firstMove.getY());
        verify(collection, times(1)).saveMove(firstMove);

        TicTacToeBean secondMove = new TicTacToeBean(2,1,2,'O');
        game.play(secondMove.getX(), secondMove.getY());
        verify(collection, times(1)).saveMove(secondMove);
    }




}
