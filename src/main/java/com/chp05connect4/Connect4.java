package com.chp05connect4;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Connect4 {

    private static final int BOARD_ROWS = 6;
    private static final int BOARD_COLUMNS = 7;
    private static final String EMPTY_SPOT = " ";
    private static final String RED_PLAYER = "R";
    private static final String GREEN_PLAYER = "G";
    private static final String BOARD_DELIMITER = "|";
    private final String[][] board;
    private String currentPlayer;
    private PrintStream outputChannel;

    public Connect4(PrintStream out){
        outputChannel = out;
        board = new String[BOARD_ROWS][BOARD_COLUMNS];
        for (String[] row: board){
            Arrays.fill(row, EMPTY_SPOT);
        }
        currentPlayer = RED_PLAYER;
    }

    public int getNumberOfDiscs(){
        return IntStream.range(0, BOARD_COLUMNS).map(this::getNumberOfDiscsInColumn).sum();
    }

    private int getNumberOfDiscsInColumn(final int column) {
        return (int) IntStream.range(0, BOARD_ROWS).filter(row -> !EMPTY_SPOT.equals(board[row][column])).count();
    }

    public int putDiscInColumn(final int column) throws RuntimeException{
        checkColumn(column);
        int row = getNumberOfDiscsInColumn(column);
        checkPositionToInsert(row, column);
        board[row][column] = "R";
        printBoard();
        switchPlayer();
        return row;
    }

    private void checkColumn(final int column) throws RuntimeException{
        if (column < 0 || column >= BOARD_COLUMNS){
            throw new RuntimeException("Invalid Column " + column);
        }
    }


    private void checkPositionToInsert(final int row, final int column) {
        if (row == BOARD_ROWS){
            throw new RuntimeException("No more room in column " + column);
        }
    }

    public String getCurrentPlayer() {
        outputChannel.printf("Player %s turn%n", currentPlayer);
        return currentPlayer;
    }

    private void switchPlayer(){
        currentPlayer = RED_PLAYER.equals(currentPlayer) ? GREEN_PLAYER : RED_PLAYER;
    }

    private void printBoard(){
        for (int row = BOARD_ROWS - 1; row >= 0; row-- ){
            StringJoiner stringJoiner = new StringJoiner(BOARD_DELIMITER, BOARD_DELIMITER, BOARD_DELIMITER);
            Stream.of(board[row]).forEachOrdered(stringJoiner::add);
            outputChannel.println(stringJoiner.toString());
        }
    }

    public boolean isFinished(){
        return getNumberOfDiscs() == BOARD_ROWS * BOARD_COLUMNS;
    }
}
