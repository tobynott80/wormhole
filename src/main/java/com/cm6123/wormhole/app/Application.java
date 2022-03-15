package com.cm6123.wormhole.app;

import com.cm6123.wormhole.board.GameBoard;
import com.cm6123.wormhole.board.Wormhole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;

public final class Application {
    /**
     * Create a logger for the class.
     */
    private static Logger logger = LoggerFactory.getLogger(Application.class);


    private Application() {
    }

    /**
     * main entry point.  If args provided, result is displayed and program exists. Otherwise, user is prompted for
     * input.
     *
     * @param args command line args.
     */
    public static void main(final String[] args) {


        logger.info("Application Started with args:{}", String.join(",", args));

        System.out.println("Hello World.  Welcome to Wormhole.");
        boolean playAgain = true;
        while (playAgain) {

            //code adapted from:
            // https://stackoverflow.com/questions/3059333/validating-input-using-java-util-scanner

            Scanner sc = new Scanner(System.in);
            int boardWidth;
            do {
                System.out.println("Please enter the width dimension of your board? (integer between 5-10)");
                while (!sc.hasNextInt()) { //ensuring a valid number is entered.
                    System.out.println("Please enter a number between 5 and 10");
                    sc.next();
                }
                boardWidth = sc.nextInt();
            } while (boardWidth < 5 || boardWidth > 10); //ensure that the entered width is within the valid range.
            logger.info("Creating a gameboard with width " + boardWidth + " and of size " + boardWidth * boardWidth);
            GameBoard gb = new GameBoard(boardWidth);
            gb.initialiseWormholes();
            System.out.println("Thank you. The board has " + boardWidth * boardWidth + " squares. " + "There are wormhole entrances at" + gb.getEntryHoles() + " and wormhole exits at");


            String usrIput = "";
            boolean validInput = false;
            while (!validInput) {
                System.out.println("Would you like to play again? (Type 'Y' to play again, or 'N' to exit)");
                Scanner inputObj = new Scanner(System.in);
                usrIput = inputObj.nextLine();
                if (usrIput.toUpperCase().equals("Y")) {
                    validInput = true;
                } else if (usrIput.toUpperCase().equals("N")) {
                    validInput = true;
                    playAgain = false;
                }
            }

        }


        logger.info("Application closing");
    }




}
