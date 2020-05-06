package com.chp03tictactoe;

public class TicTacToe {

    private char[][] board;
    private final char EMPTY_SPOT = '-';
    private final char NO_CURRENT_PLAYER = '-';
    private final char X_PLAYER = 'X';
    private final char O_PLAYER = 'O';
    private char currentPlayer;

    public TicTacToe(){
        board = new char[][]{
                {EMPTY_SPOT, EMPTY_SPOT, EMPTY_SPOT},
                {EMPTY_SPOT, EMPTY_SPOT, EMPTY_SPOT},
                {EMPTY_SPOT, EMPTY_SPOT, EMPTY_SPOT}
        };
        currentPlayer = NO_CURRENT_PLAYER;
    }

    public void play(final int xPosition, final int yPosition) throws RuntimeException {
        validateAxisPostion(xPosition);
        validateAxisPostion(yPosition);
        placePiece(xPosition, yPosition);
        currentPlayer = nextPlayer();
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

    public char nextPlayer(){
        return currentPlayer == X_PLAYER ? O_PLAYER : X_PLAYER;
    }
}
