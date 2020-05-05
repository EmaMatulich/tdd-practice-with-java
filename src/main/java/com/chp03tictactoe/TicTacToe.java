package com.chp03tictactoe;

public class TicTacToe {

    private static final String X_PIECE = "X";
    private static final String O_PIECE = "O";

    private String[][] board = new String[3][3];

    public void placePiece(String piece, int xAxis, int yAxis) throws RuntimeException{
        validateXAxisPosition(xAxis);
        validateYAxisPosition(yAxis);
        int realXAxis = --xAxis;
        int realYAxis = --yAxis;
        validateIfPositionIsOcupated(realXAxis, realYAxis);
        board[realXAxis][realYAxis] = piece;
    }

    private void validateXAxisPosition(int xAxis) throws RuntimeException{
        if(xAxis < 0 || xAxis > 3){
            throw new RuntimeException("X-Axis out of range, should be between 1-3");
        }
    }

    private void validateYAxisPosition(int yAxis) throws RuntimeException{
        if(yAxis < 0 || yAxis > 3){
            throw new RuntimeException("Y-Axis out of range, should be between 1-3");
        }
    }

    private void validateIfPositionIsOcupated(int xAxis, int yAxis) throws RuntimeException{
        String pieceAtPosition = board[xAxis][yAxis];
        if (pieceAtPosition != null && (X_PIECE.equals(pieceAtPosition) || pieceAtPosition.equals(O_PIECE))) {
            StringBuilder builder = new StringBuilder();
            builder
                    .append("Position ").append(xAxis).append(" - ")
                    .append(yAxis).append(" is already ocupated.");
            throw new RuntimeException(builder.toString());
        }
    }

}
