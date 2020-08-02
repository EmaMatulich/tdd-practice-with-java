package com.chp06tictactoev2;

import com.mongodb.MongoException;
import org.jongo.MongoCollection;
import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TicTacToeCollectionSpec {

    private TicTacToeCollection collection;
    private TicTacToeBean bean;
    private MongoCollection mongoCollection;

    @Before
    public void setUp() throws UnknownHostException {
        collection = spy(new TicTacToeCollection());
        bean = new TicTacToeBean(3, 2, 1, 'Y');
        mongoCollection = mock(MongoCollection.class);
    }

    @Test
    public void whenInstantiatedThenMongoHasDbNameTicTacToe(){
        assertEquals("tic-tac-toe", collection.getMongoCollection().getDBCollection().getDB().getName());
    }

    @Test
    public void whenInstantiatedThenMongoCollectionHasNameGame(){
        assertEquals("game", collection.getMongoCollection().getName());
    }

    @Test
    public void whenSaveMoveThenInvokeMongoCollectionSave(){
        doReturn(mongoCollection).when(collection).getMongoCollection();

        collection.saveMove(bean);

        verify(mongoCollection, times(1)).save(bean);

    }


    @Test
    public void whenSaveMoveThenReturnTrue(){
        doReturn(mongoCollection).when(collection).getMongoCollection();

        assertTrue(collection.saveMove(bean));
    }


    @Test
    public void givenExceptionWhenSaveMoveThenReturnFalse(){

        doThrow(new MongoException("mongo exception"))
                .when(mongoCollection).save(any(TicTacToeBean.class));

        doReturn(mongoCollection).when(collection).getMongoCollection();

        assertFalse(collection.saveMove(bean));
    }

    @Test
    public void whenDropThenInvokeMongoCollectionDrop(){
        doReturn(mongoCollection).when(collection).getMongoCollection();

        collection.drop();

        verify(mongoCollection).drop();
    }


    @Test
    public void whenDropThenThenReturnTrue(){
        doReturn(mongoCollection).when(collection).getMongoCollection();

        assertTrue(collection.drop());
    }


    @Test
    public void givenExceptionWhenDropThenReturnFalse(){

        doThrow(new MongoException("Mongo exception")).when(mongoCollection).drop();

        doReturn(mongoCollection).when(collection).getMongoCollection();

        assertFalse(collection.drop());

    }
}
