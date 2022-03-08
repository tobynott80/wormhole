package com.cm6123.wormhole.board;

import java.util.Scanner;

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
     *  Constructor when no width given to ask the user for their specified width of the gameboard.
     */
    public GameBoard() {

         //code adapted from:
         // https://stackoverflow.com/questions/3059333/validating-input-using-java-util-scanner

        Scanner sc = new Scanner(System.in);

        int number;
        do {
            System.out.println("Please enter the width dimension of your board? (integer between 5-10)");
            while (!sc.hasNextInt()) { //ensuring a valid number is entered.
                System.out.println("Please enter a number between 5 and 10");
                sc.next();
            }
            number = sc.nextInt();
        } while (number < 5 || number > 10); //ensure that the entered width is within the valid range.
        boardWidth = number;

        System.out.println("Creating a gameboard with width " + boardWidth + " and of size " + boardWidth * boardWidth);
    }

    /**
     * @return the size of the board (no. of squares)
     */
    public int getBoardSize() {
        return boardWidth * boardWidth;
    }


}
