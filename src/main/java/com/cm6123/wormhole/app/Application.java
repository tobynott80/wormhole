package com.cm6123.wormhole.app;

import com.cm6123.wormhole.board.GameBoard;
import com.cm6123.wormhole.dice.DiceMode;
import com.cm6123.wormhole.player.PlayerController;
import org.apache.logging.slf4j.Log4jLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;

public final class Application {
    /**
     * Create a logger for the class.
     */
    private static final Logger logger = LoggerFactory.getLogger(Application.class);



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
                    logger.info("Non-int entered, trying again");
                    System.out.println("Please enter a number between 5 and 10");
                    sc.next();
                }
                boardWidth = sc.nextInt();
            } while (boardWidth < 5 || boardWidth > 10); //ensure that the entered width is within the valid range.
            logger.info("Creating a gameboard with width " + boardWidth + " and of size " + boardWidth * boardWidth);
            GameBoard gb = new GameBoard(boardWidth);
            logger.info("Created new gameboard");
            gb.initialiseWormholes();
            logger.info("Initialised wormholes");
            System.out.println("Thank you. The board has " + boardWidth * boardWidth + " squares. "
                    + "\nThere are wormhole entrances at " + gb.getEntryHoles()
                    + "\nand wormhole exits at " + gb.getExitHoles());

            int noOfPlayers;
            do {
                System.out.println("Please enter the number of players? (integer between 2-6)");
                while (!sc.hasNextInt()) { //ensuring a valid number is entered.
                    System.out.println("Please enter a number between 2 and 6");
                    sc.next();
                }
                noOfPlayers = sc.nextInt();
            } while (noOfPlayers < 2 || noOfPlayers > 6); //ensure that the entered number of players is within the valid range.

            PlayerController controller = new PlayerController(noOfPlayers);
            controller.initialisePlayers(1);
            for (int i = 0; i < noOfPlayers; i++) {
                System.out.println("Please enter the name of player " + i+1 + "?");
                Scanner inputObj = new Scanner(System.in);
                String usrIput;
                usrIput = inputObj.nextLine();
                controller.namePlayer(i, usrIput);
                logger.info("Named player " + i +1 + usrIput);
            }

            for (int i = 0; i < noOfPlayers; i++) {
                boolean validInput = false;
                while (!validInput) {
                    System.out.println(" - do you want to roll the dice, or shall I do it for you? Type “Y” to roll yourself or “N” to let me do it.");
                    Scanner inputObj = new Scanner(System.in);
                    String usrIput;
                    usrIput = inputObj.nextLine();
                    if (usrIput.toUpperCase().equals("Y")) {
                        validInput = true;
                        controller.diceMode(i, DiceMode.manual);
                    } else if (usrIput.toUpperCase().equals("N")) {
                        controller.diceMode(i, DiceMode.automatic);
                        validInput = true;

                    }
                }
            }



            String usrIput = "";
            boolean validInput = false;
            while (!validInput) {
                System.out.println("Would you like to play again? (Type 'Y' to play again, or 'N' to exit)");
                Scanner inputObj = new Scanner(System.in);
                usrIput = inputObj.nextLine();
                if (usrIput.toUpperCase().equals("Y")) {
                    validInput = true;
                    logger.info("User wished to play again.");
                } else if (usrIput.toUpperCase().equals("N")) {
                    validInput = true;
                    playAgain = false;
                    logger.info("Player wishes to end application");
                }
            }

        }


        logger.info("Application closing");
    }




}
