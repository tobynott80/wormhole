package com.cm6123.wormhole.player;

/**
 * Class to manage an individual player and their actions
 */
public class Player {

    private int position;

    public Player(int startingPosition) {
        position = startingPosition;
    }

    public Player() {
        position = 1;
    }

    public int getPosition() {
        return position;
    }

    /**
     * @param squareDistance How many squares should player be moved
     */
    public void movePlayer(int squareDistance) {
        int targetSquare = this.getPosition() + squareDistance;
        System.out.println(targetSquare);
        position = targetSquare;
    }
}
