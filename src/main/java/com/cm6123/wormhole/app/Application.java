package com.cm6123.wormhole.app;

import com.cm6123.wormhole.board.GameBoard;
import com.cm6123.wormhole.board.WormholeEntry;
import com.cm6123.wormhole.board.WormholeType;
import com.cm6123.wormhole.dice.Dice;
import com.cm6123.wormhole.dice.DiceMode;
import com.cm6123.wormhole.player.PlayerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public final class Application {
    /**
     * Create a logger for the class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);



    private Application() {
    }

    /**
     * main entry point.  If args provided, result is displayed and program exists. Otherwise, user is prompted for
     * input.
     *
     * @param args command line args.
     */
    public static void main(final String[] args) {


        LOGGER.info("Application Started with args:{}", String.join(",", args));

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
                    LOGGER.info("Non-int entered, trying again");
                    System.out.println("Please enter a number between 5 and 10");
                    sc.next();
                }
                boardWidth = sc.nextInt();
            } while (boardWidth < 5 || boardWidth > 10); //ensure that the entered width is within the valid range.
            LOGGER.info("Creating a gameboard with width " + boardWidth + " and of size " + boardWidth * boardWidth);
            GameBoard gb = new GameBoard(boardWidth);
            LOGGER.info("Created new gameboard");
            gb.initialiseWormholes();
            LOGGER.info("Initialised wormholes");
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
                System.out.println("Please enter the name of player " + (i+1) + "?");
                Scanner inputObj = new Scanner(System.in);
                String usrIput;
                usrIput = inputObj.nextLine();
                controller.namePlayer(i, usrIput);
                LOGGER.info("Named player " + (i+1) + ": " + usrIput);
            }

            for (int i = 0; i < noOfPlayers; i++) {
                boolean validInput = false;
                while (!validInput) {
                    System.out.println(controller.getName(i) + " - do you want to roll the dice, or shall I do it for you? Type 'Y' to roll yourself or 'N' to let me do it.");
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

            while(!controller.gameOver()){
                int currentPlayer = controller.getCurrentPlayer();
                System.out.println(controller.getName(currentPlayer) + " (Player " + (currentPlayer+1) + ") - It's Your Turn");
                System.out.println("You are currently on square " + controller.getPosition(currentPlayer));
                int newLocation;
                int squareDistance;
                Integer roll1;
                Integer roll2;
                if (controller.getDice(currentPlayer) == DiceMode.automatic){ //If the user has selected automatic dice rolling
                    Dice aDice = new Dice(6);
                    roll1 = aDice.roll();
                    roll2 = aDice.roll();
                    System.out.println(controller.getName(currentPlayer) + " rolls a " + roll1 + " and a " + roll2);
                } else { //If user is rolling their own dice.
                    do {
                        System.out.println("Please enter the result of your first dice roll (integer between 1-6)");
                        while (!sc.hasNextInt()) { //ensuring a valid number is entered.
                            LOGGER.info("Non-int entered, trying again");
                            System.out.println("Invalid Entry - Please enter a number between 1 and 6");
                            sc.next();
                        }
                        roll1 = sc.nextInt();
                    } while (roll1 < 1 || roll1 > 6); //ensure that the entered dice roll is within the valid range.
                    do {
                        System.out.println("Please enter the result of your second dice roll (integer between 1-6)");
                        while (!sc.hasNextInt()) { //ensuring a valid number is entered.
                            LOGGER.info("Non-int entered, trying again");
                            System.out.println("Invalid Entry - Please enter a number between 1 and 6");
                            sc.next();
                        }
                        roll2 = sc.nextInt();
                    } while (roll2 < 1 || roll2 > 6); //ensure that the entered dice roll is within the valid range.
                }
                newLocation = controller.getPosition(currentPlayer) + roll1 + roll2;
                squareDistance = roll1 + roll2;

                if (controller.checkWormholes(newLocation)) { //if the player is going to land on a wormhole
                    WormholeEntry currentWormhole = gb.getEntryHole(newLocation);
                    System.out.println(controller.getName(currentPlayer) + " moves to square " + newLocation + " which is a " + currentWormhole.getPolarity() + " wormhole...");
                    if(currentWormhole.getPolarity().equals(WormholeType.negative) && roll1.equals(roll2)){
                        System.out.println("You have landed on a negative wormhole, but not to worry, you also rolled a double, so you stay where you are");
                    } else {
                        controller.movePlayer(currentPlayer, squareDistance);
                        System.out.println(controller.getName(currentPlayer) + " has moved to exit wormhole " + controller.getPosition(currentPlayer));
                    }
                } else { //if the player does not land on a wormhole
                    controller.movePlayer(currentPlayer, squareDistance);
                    System.out.println(controller.getName(currentPlayer) + " has moved to square " + controller.getPosition(currentPlayer));
                }


                controller.nextPlayer();
            }
            System.out.println("Cogratulations " + controller.getWinner().getName() + "! You won!");


            String usrIput = "";
            boolean validInput = false;
            while (!validInput) {
                System.out.println("Would you like to play again? (Type 'Y' to play again, or 'N' to exit)");
                Scanner inputObj = new Scanner(System.in);
                usrIput = inputObj.nextLine();
                if (usrIput.toUpperCase().equals("Y")) {
                    validInput = true;
                    LOGGER.info("User wished to play again.");
                } else if (usrIput.toUpperCase().equals("N")) {
                    validInput = true;
                    playAgain = false;
                    LOGGER.info("Player wishes to end application");
                }
            }

        }


        LOGGER.info("Application closing");
    }




}
