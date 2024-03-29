package com.cm6123.wormhole.player;

import com.cm6123.wormhole.board.GameBoard;
import com.cm6123.wormhole.board.WormholeEntry;
import com.cm6123.wormhole.dice.DiceMode;

import java.util.ArrayList;


/**
 * Class to manage an individual player and their actions.
 */
public class Player {

    /**
     * Integer variable with the square position of the current location of the player.
     */
    private int position;

    /**
     * Name of the player.
     */
    private String name = "UnNamed";
    /**
     * Selected dice mode of player - manual vs automatic.
     */
    private DiceMode diceMode;

    /**
     * Constructor for mostly testing purposes to define the players initial starting position.
     *
     * @param startingPosition Location for where player starts. Default is 1
     */
    public Player(final int startingPosition) {
        position = startingPosition;
    }

    /**
     * Constructor creating new player at position 1.
     */
    public Player() {
        this.position = 1;
    }

    /**
     * @return Returns the current position of the player.
     */
    public int getPosition() {
        return position;
    }

    /**
     * Main function to move the player after rolling dice.
     *
     * @param squareDistance How many squares should player be moved
     */
    public void movePlayer(final int squareDistance) {
        int targetSquare = this.getPosition() + squareDistance;
        position = targetSquare; //move the player to the target square
        ArrayList<WormholeEntry> entryWormholeList = GameBoard.getEntryWormholeList();
        for (WormholeEntry wormhole : entryWormholeList) {
            if (targetSquare == wormhole.getPosition()) {
                //player has landed on a wormhole, change postion to the nearest exit wormhole
                //If no exit wormhole is provided, .getExit() will just return the current position.
                position = wormhole.getExit();
            }
        }


    }

    /**
     * Method to assign an individual players name.
     * @param playerName Name of player
     */
    public void assignName(final String playerName) {
        this.name = playerName;
    }

    /**
     * @return Returns the name of player.
     */
    public String getName() {
        return name;
    }

    /**
     * Assign chosen dice roll mode.
     * @param diceType Type of dice roll - automatic/manual.
     */
    public void assignDiceMode(final DiceMode diceType) {
        this.diceMode = diceType;
    }

    /**
     * @return Returns the chosen mode of rolling dice.
     */
    public DiceMode getDiceMode() {
        return this.diceMode;
    }
}
