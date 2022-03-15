package com.cm6123.wormhole.player;

import com.cm6123.wormhole.board.GameBoard;
import com.cm6123.wormhole.board.WormholeEntry;


/**
 * Class to manage an individual player and their actions.
 */
public class Player {

    /**
     * Integer variable with the square position of the current location of the player.
     */
    private int position;

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
        position = targetSquare; //move the player to the
        for (WormholeEntry wormhole : GameBoard.entryWormholeList) {
            if (targetSquare == wormhole.getPosition()) {
                //player has landed on a wormhole, change postion to the nearest exit wormhole
                //If no exit wormhole is provided, .getExit() will just return the current position.
                position = wormhole.getExit();
            }
        }


    }
}
