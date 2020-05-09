package com.chp03tictactoe.wildtictactoe;

import java.util.HashSet;
import java.util.Set;

public class WildTicTacToe {

    private static final String PLAYER_1 = "Player 1";
    private static final String PLAYER_2 = "Player 2";
    private static final String PIECE_X = "X";
    private static final String PIECE_O = "O";
    private static final String EMPTY_SPOT = "-";
    private static final int BOARD_SIZE = 3;
    private static final String X_VICTORY = "XXX";
    private static final String O_VICTORY = "OOO";
    private static final Set<String> ALLOWED_PIECES = new HashSet<>();
    private String[][] board;
    private String currentPlayer;


    public WildTicTacToe() {
        ALLOWED_PIECES.add(PIECE_X);
        ALLOWED_PIECES.add(PIECE_O);
        board = new String[][]{
                {EMPTY_SPOT, EMPTY_SPOT, EMPTY_SPOT},
                {EMPTY_SPOT, EMPTY_SPOT, EMPTY_SPOT},
                {EMPTY_SPOT, EMPTY_SPOT, EMPTY_SPOT}};
    }


    public String play(final int x, final int y, String piece) throws RuntimeException {
        validAxisPosition(x);
        validAxisPosition(y);
        validPiecePlayed(piece);
        int realX = x - 1;
        int realY = y - 1;
        validSpotCondition(realX, realY);
        nextPlayer();
        board[realX][realY] = piece;
        if (win(realX, realY, piece)) {
            return currentPlayer + " is the winner";
        }
        if(draw()){
            return "The result is a draw";
        }
        return "Game is not over";
    }

    public void validAxisPosition(int position) throws RuntimeException {
        if (position < 1 || position > 3) {
            throw new RuntimeException("Position is out of board range");
        }
    }

    public void validPiecePlayed(String piece) throws RuntimeException {
        if (!ALLOWED_PIECES.contains(piece)) {
            throw new RuntimeException("Invalid piece was played");
        }
    }

    public void validSpotCondition(final int x, final int y) throws RuntimeException {
        if (!board[x][y].equals(EMPTY_SPOT)) {
            throw new RuntimeException("Place already occupied");
        }
    }

    public String nextPlayer() {
        currentPlayer = !PLAYER_1.equals(currentPlayer) ? PLAYER_1 : PLAYER_2;
        return currentPlayer;
    }

    private boolean win(int x, int y, String piece) {
        String lineToWin = piece.equals(PIECE_X) ? X_VICTORY : O_VICTORY;
        String horizontal = "";
        String vertical = "";
        String mainDiagonal = "";
        String secondaryDiagonal = "";
        for (int i = 0; i < BOARD_SIZE; i++) {
            horizontal += board[x][i];
            vertical += board[i][y];
            mainDiagonal += board[i][i];
            secondaryDiagonal += board[i][BOARD_SIZE - 1 - i];
        }
        if (lineToWin.equals(horizontal)
                || lineToWin.equals(vertical)
                || lineToWin.equals(mainDiagonal)
                || lineToWin.equals(secondaryDiagonal))
        {
            return true;
        }
        return false;
    }

    public boolean draw(){
        for(int i=0; i < BOARD_SIZE; i++){
            for(int j = 0; j< BOARD_SIZE; j++){
                if (EMPTY_SPOT.equals(board[i][j])){
                    return false;
                }
            }
        }
        return true;
    }

}
