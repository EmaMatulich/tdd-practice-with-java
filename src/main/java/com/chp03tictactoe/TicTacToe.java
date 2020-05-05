package com.chp03tictactoe;

public class TicTacToe {

    private char[][] board;
    private final char EMPTY_SPOT = '-';
    private final char X_PLAYER = 'X';

    public TicTacToe(){
        board = new char[][]{
                {EMPTY_SPOT, EMPTY_SPOT, EMPTY_SPOT},
                {EMPTY_SPOT, EMPTY_SPOT, EMPTY_SPOT},
                {EMPTY_SPOT, EMPTY_SPOT, EMPTY_SPOT}
        };
    }

    public void play(final int xPosition, final int yPosition) throws RuntimeException {
        validateAxisPostion(xPosition);
        validateAxisPostion(yPosition);
        placePiece(xPosition, yPosition);
    }

    private void validateAxisPostion(final int axisPosition) throws RuntimeException{
        if (axisPosition < 1 || axisPosition > 3){
            throw new RuntimeException("Position is out of range, it should be between (1-3, 1-3).");
        }
    }

    private void placePiece(final int xPosition, final int yPosition){
        int realX = xPosition - 1;
        int realY = yPosition - 1;

        if (board[realX][realY] != EMPTY_SPOT){
            throw new RuntimeException("Position "+ xPosition + " - " + yPosition + "is occupied");
        }
        board[realX][realY] = X_PLAYER;
    }
}
