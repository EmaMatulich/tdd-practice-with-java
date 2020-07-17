package com.chp06tictactoev2;

import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;

import static org.junit.Assert.*;

public class TicTacToeCollectionSpec {

    private TicTacToeCollection collection;

    @Before
    public void setUp() throws UnknownHostException {
        collection = new TicTacToeCollection();
    }

    @Test
    public void whenInstantiatedThenMongoHasDbNameTicTacToe(){
        assertEquals("tic-tac-toe", collection.getMongoCollection().getDBCollection().getDB().getName());
    }

    @Test
    public void whenInstantiatedThenMongoCollectionHasNameGame(){
        assertEquals("game", collection.getMongoCollection().getName());
    }

}
