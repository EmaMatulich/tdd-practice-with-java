package com.chp06tictactoev2;

import java.net.UnknownHostException;

public class TicTacToe {


    private final char EMPTY_SPOT = '-';
    private final char NO_CURRENT_PLAYER = '-';
    private final char X_PLAYER = 'X';
    private final char O_PLAYER = 'O';
    private final String NO_WINNER_MESSAGE = "No winner";
    private final String DRAW_MESSAGE = "The result is a draw";
    private final int BOARD_SIZE = 3;
    private char[][] board;
    private char currentPlayer;

    private TicTacToeCollection collection;

    public TicTacToe() throws UnknownHostException {
        this(new TicTacToeCollection());
    }

    protected TicTacToe(TicTacToeCollection collection) {
        board = new char[][]{
                {EMPTY_SPOT, EMPTY_SPOT, EMPTY_SPOT},
                {EMPTY_SPOT, EMPTY_SPOT, EMPTY_SPOT},
                {EMPTY_SPOT, EMPTY_SPOT, EMPTY_SPOT}
        };
        currentPlayer = NO_CURRENT_PLAYER;
        this.collection = collection;
    }

    public String play(final int xPosition, final int yPosition) throws RuntimeException {
        validateAxisPostion(xPosition);
        validateAxisPostion(yPosition);
        currentPlayer = nextPlayer();
        placePiece(xPosition, yPosition, currentPlayer);
        TicTacToeBean move = new TicTacToeBean();
        collection.saveMove();
        if (win(xPosition, yPosition)) {
            return currentPlayer + " is the winner";
        }
        if (draw()) {
            return DRAW_MESSAGE;
        }
        return NO_WINNER_MESSAGE;
    }

    private void validateAxisPostion(final int axisPosition) throws RuntimeException {
        if (axisPosition < 1 || axisPosition > 3) {
            throw new RuntimeException("Position is out of range, it should be between (1-3, 1-3).");
        }
    }

    private void placePiece(final int xPosition, final int yPosition, char player) {
        int realX = xPosition - 1;
        int realY = yPosition - 1;

        if (board[realX][realY] != EMPTY_SPOT) {
            throw new RuntimeException("Position " + xPosition + " - " + yPosition + "is occupied");
        }
        board[realX][realY] = player;
    }

    public char nextPlayer() {
        return currentPlayer == X_PLAYER ? O_PLAYER : X_PLAYER;
    }

    private boolean win(final int xPosition, final int yPosition) {
        int playerTotal = currentPlayer * BOARD_SIZE;
        char horizontal, vertical, mainDiagonal, secondaryDiagonal;
        horizontal = vertical = mainDiagonal = secondaryDiagonal = '\0';
        for (int i = 0; i < BOARD_SIZE; i++) {
            horizontal += board[xPosition - 1][i];
            vertical += board[i][yPosition - 1];
            mainDiagonal += board[i][i];
            secondaryDiagonal += board[i][BOARD_SIZE - i - 1];
        }

        if (playerTotal == mainDiagonal
                || playerTotal == secondaryDiagonal
                || playerTotal == horizontal
                || playerTotal == vertical) {
            return true;
        }
        return false;
    }

    public boolean draw() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == EMPTY_SPOT) {
                    return false;
                }
            }
        }
        return true;
    }

    public TicTacToeCollection getTicTacToeCollection(){
        return this.collection;
    }
}
