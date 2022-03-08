package com.cm6123.wormhole.app;

import com.cm6123.wormhole.board.GameBoard;
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

            GameBoard gb = new GameBoard();

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
