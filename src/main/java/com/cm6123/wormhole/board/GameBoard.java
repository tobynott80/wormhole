package com.cm6123.wormhole.board;


/**
 * Class to manage the actions and methods of the gameboard.
 */
public final class GameBoard {

    /**
     * Integer to define the width of the board.
     */
    private int boardWidth;

    /**
     * @param width with of the gameboard (between 5 and 10 in deployment).
     */
    public GameBoard(final int width) {
        this.boardWidth = width;

    }



    /**
     * @return the size of the board (no. of squares)
     */
    public int getBoardSize() {
        return boardWidth * boardWidth;
    }


    public void addPositiveWormhole(int entry, int exit) {


    }
}
