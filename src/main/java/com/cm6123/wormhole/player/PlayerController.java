package com.cm6123.wormhole.player;

import com.cm6123.wormhole.board.GameBoard;
import com.cm6123.wormhole.board.Wormhole;

import java.util.ArrayList;

/**
 * Class to combine players together and handle interactions across all players.
 */
public final class PlayerController {
    /**
     * Number of players requested by the user(s).
     */
    private final int noOfPlayers;

    /**
     * Array list to hold each player objects.
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
     * @param startingPosition Starting board position. For testing use only, default value 1.
     */
    public void initialisePlayers(final int startingPosition) {

        int i = 0;
        while (i < noOfPlayers) {
            playerList.add(new Player(startingPosition));
            i++;
        }
    }

    /**
     * Method to check if game is over.
     * @param noOfSquares number of squares on gameboard
     * @return Boolean returned to determine if game is over or not
     */
    public boolean gameOver() {
        int noOfSquares = GameBoard.getBoardSize();
        for (Player player : playerList) {
            if (player.getPosition() >= noOfSquares){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    /**
     * Checks if a player has landed on a wormhole.
     * @return True if on wormhole, false if not.
     */
    public boolean checkWormholes() {
        for (Player player: playerList) {
            for (Wormhole wormhole: GameBoard.entryWormholeList){
                if (player.getPosition() == wormhole.getPosition()){
                    //player has landed on a wormhole
                    return true;
                }
            }

        }
        return false;
    }
}
