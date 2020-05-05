package com.chp03tictactoe;

public class TicTacToe {


    public void placePiece(String piece, int xAxis, int yAxis) throws RuntimeException{
        if(xAxis < 0 || xAxis > 3){
            throw new RuntimeException("X-Axis out of range, should be between 1-3");
        }
    }

}
