package com.cm6123.wormhole.player;

import java.util.ArrayList;

/**
 * Class to combine players together and handle interactions across all players.
 */
public class PlayerController {
    /**
     * Number of players requested by the user(s).
     */
    private int noOfPlayers;

    /**
     * Array list to hold each player objects
     */
    public ArrayList<Player> playerList = new ArrayList<Player>();

    /**
     * Constructor to supply number of player.
     * @param players number of players specified
     */
    public PlayerController(final int players) {
        noOfPlayers = players;

    }

    /**
     * @param startingPosition Starting board position. For testing use only, default value 1
     */
    public void initialisePlayers(final int startingPosition) {

        int i = 0;
        while (i < noOfPlayers) {
            playerList.add(new Player(startingPosition));
            i++;
        }
    }

    public boolean gameOver(final int noOfSquares) {
        for (Player player : playerList) {
            if (player.getPosition() > noOfSquares){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
}
