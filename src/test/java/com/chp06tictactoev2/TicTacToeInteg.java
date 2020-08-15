package com.chp06tictactoev2;

import org.junit.Assert;
import org.junit.Test;

import java.net.UnknownHostException;

public class TicTacToeInteg {

    @Test
    public void givenMongoDbIsRunningWhenPlayThenNotException() throws UnknownHostException {
       TicTacToe game = new TicTacToe();
        Assert.assertEquals("No winner", game.play(1,1));
    }

}
