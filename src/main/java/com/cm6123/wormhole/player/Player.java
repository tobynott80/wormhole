package com.cm6123.wormhole.player;

/**
 * Class to manage an individual player and their actions.
 */
public final class Player {

    /**
     * Integer variable with the square position of the current location of the player.
     */
    private int position;

    /**
     * Constructor for mostly testing purposes to define the players initial starting position.
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
     * @param squareDistance How many squares should player be moved
     */
    public void movePlayer(final int squareDistance) {
        int targetSquare = this.getPosition() + squareDistance;
        System.out.println(targetSquare);
        position = targetSquare;
    }
}
