package com.cm6123.wormhole.player;

import com.cm6123.wormhole.board.GameBoard;
import com.cm6123.wormhole.board.Wormhole;
import com.cm6123.wormhole.dice.DiceMode;

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
     * Which player number is the current player.
     */
    private Integer currentPlayer = 0;

    /**
     * Constructor to supply number of player.
     * @param players number of players specified
     */
    public PlayerController(final int players) {
        this.noOfPlayers = players;
        this.playerList = new ArrayList<Player>();
        this.currentPlayer = 0;
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
     * @return Boolean returned to determine if game is over or not
     */
    public boolean gameOver() {
        int noOfSquares = GameBoard.getBoardSize();
        for (Player player : playerList) {
            if (player.getPosition() >= noOfSquares){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a player has landed on a wormhole.
     * @return True if on wormhole, false if not.
     */
    public boolean checkWormholes(final int testLocation) {
        for (Player player: playerList) {
            for (Wormhole wormhole: GameBoard.entryWormholeList){
                if (testLocation == wormhole.getPosition()){
                    //player has landed on a wormhole
                    return true;
                }
            }

        }
        return false;
    }

    /**
     * Assign name to player method.
     * @param playerNo Player number of playerlist
     * @param name String name of player
     */
    public void namePlayer(final int playerNo, final String name) {
        this.playerList.get(playerNo).assignName(name);
    }

    /**
     * @param playerNo The player number
     * @return Returns string the name of player
     */
    public String getName(final int playerNo) {
        return this.playerList.get(playerNo).getName();
    }

    /**
     * @param playerNo Player Number
     * @param diceType Type of dice roll chosen - auto/manual
     */
    public void diceMode(final int playerNo, final DiceMode diceType) {
        this.playerList.get(playerNo).assignDiceMode(diceType);
    }

    /**
     * @param playerNo Player Number
     * @return Returns the chosen dice mode
     */
    public DiceMode getDice(final int playerNo) {
        return this.playerList.get(playerNo).getDiceMode();
    }

    public int getCurrentPlayer(){
        return this.currentPlayer;
    }

    /**
     * Method to go to next player.
     * @return Returns the new current player
     */
    public int nextPlayer() {
        if (!this.currentPlayer.equals(this.noOfPlayers)){
            this.currentPlayer++;
        } else {
            this.currentPlayer = 0;
        }
        return this.currentPlayer;
    }

    public int getPostion(int playerNo) {
        return this.playerList.get(playerNo).getPosition();
    }
}
