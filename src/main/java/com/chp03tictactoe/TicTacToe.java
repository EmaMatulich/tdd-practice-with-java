package com.chp03tictactoe;

public class TicTacToe {


    private final char EMPTY_SPOT = '-';
    private final char NO_CURRENT_PLAYER = '-';
    private final char X_PLAYER = 'X';
    private final char O_PLAYER = 'O';
    private final String NO_WINNER_MESSAGE = "No winner";
    private final int BOARD_SIZE = 3;
    private char[][] board;
    private char currentPlayer;

    public TicTacToe(){
        board = new char[][]{
                {EMPTY_SPOT, EMPTY_SPOT, EMPTY_SPOT},
                {EMPTY_SPOT, EMPTY_SPOT, EMPTY_SPOT},
                {EMPTY_SPOT, EMPTY_SPOT, EMPTY_SPOT}
        };
        currentPlayer = NO_CURRENT_PLAYER;
    }

    public String play(final int xPosition, final int yPosition) throws RuntimeException {
        validateAxisPostion(xPosition);
        validateAxisPostion(yPosition);
        currentPlayer = nextPlayer();
        placePiece(xPosition, yPosition, currentPlayer);
        if (win()){
            return currentPlayer + " is the winner";
        }
        return NO_WINNER_MESSAGE;
    }

    private void validateAxisPostion(final int axisPosition) throws RuntimeException{
        if (axisPosition < 1 || axisPosition > 3){
            throw new RuntimeException("Position is out of range, it should be between (1-3, 1-3).");
        }
    }

    private void placePiece(final int xPosition, final int yPosition, char player){
        int realX = xPosition - 1;
        int realY = yPosition - 1;

        if (board[realX][realY] != EMPTY_SPOT){
            throw new RuntimeException("Position "+ xPosition + " - " + yPosition + "is occupied");
        }
        board[realX][realY] = player;
    }

    public char nextPlayer(){
        return currentPlayer == X_PLAYER ? O_PLAYER : X_PLAYER;
    }

    private boolean win() {
        int playerTotal = currentPlayer * BOARD_SIZE;
        int mainDiagonal = 0;
        int secondaryDiagonal = 0;
        for (int i = 0; i < BOARD_SIZE; i++){
            mainDiagonal += board[i][i];
            secondaryDiagonal += board[i][BOARD_SIZE - i - 1];
            //horizontal win
            if (board[i][0] + board[i][1] + board[i][2] == playerTotal){
                return true;
            }
            //vertical win
            if (board[0][i] + board[1][i] + board[2][i] == playerTotal){
                return true;
            }
        }
        if (playerTotal == mainDiagonal || playerTotal == secondaryDiagonal){
            return true;
        }
        return false;
    }
}
