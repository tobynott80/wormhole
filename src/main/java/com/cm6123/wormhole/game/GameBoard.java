package com.cm6123.wormhole.game;

import java.util.Scanner;

public class GameBoard {

    private int BoardWidth;
    private int BoardSize;

    public GameBoard(final int width) {
        BoardWidth = width;
        BoardSize = BoardWidth*BoardWidth;
    }

    public GameBoard(){
        Scanner sc = new Scanner(System.in);

        int number; //code adapted from https://stackoverflow.com/questions/3059333/validating-input-using-java-util-scanner
        do {
            System.out.println("Please enter the width dimension of your board? (integer between 5-10)");
            while (!sc.hasNextInt()) { //ensuring a valid number is entered
                System.out.println("Please enter a number between 5 and 10");
                sc.next();
            }
            number = sc.nextInt();
        } while (number < 5 || number > 10); //ensure that the entered width is within the valid range
        BoardWidth = number;
        BoardSize = BoardWidth*BoardWidth;
        System.out.println("Creating a gameboard with width "+BoardWidth+" and of size "+BoardSize);
    }

    public int getBoardSize() {
        return BoardSize;
    }


}
