package com.cm6123.wormhole.player;

import com.cm6123.wormhole.board.GameBoard;
import com.cm6123.wormhole.board.WormholeEntry;
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
     * @return Returns current playerlist
     */
    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    /**
     * Array list to hold each player objects.
     */
    private ArrayList<Player> playerList = new ArrayList<Player>();

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
     * Checks if a square is a wormhole.
     * @param testLocation Location of square to be tested.
     * @return True if a wormhole, false if not.
     */
    public boolean checkWormholes(final int testLocation) {
        ArrayList<WormholeEntry> entryWormholeList = GameBoard.getEntryWormholeList();
        for (Player player: playerList) {
            for (WormholeEntry wormhole: entryWormholeList){
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

    /**
     * @return Returns the current player.
     */
    public int getCurrentPlayer(){
        return this.currentPlayer;
    }

    /**
     * Method to go to next player.
     * @return Returns the new current player
     */
    public int nextPlayer() {
        if (!this.currentPlayer.equals(this.noOfPlayers - 1)){
            this.currentPlayer++;
        } else {
            this.currentPlayer = 0;
        }
        return this.currentPlayer;
    }

    /**
     * @param playerNo Player number in playerlist.
     * @return Returns the position of given player.
     */
    public int getPosition(final int playerNo) {
        return this.playerList.get(playerNo).getPosition();
    }

    /**
     * @param playerNo Player number in playerlist.
     * @param squareDistance Distance the player is to be moved.
     */
    public void movePlayer(final int playerNo, final int squareDistance) {
        playerList.get(playerNo).movePlayer(squareDistance);
    }

    /**
     * @return Returns the current winning player
     */
    public Player getWinner() {
        int highestScore = 0;
        Player winningPlayer = null;
        for (Player player: this.playerList) {
            if (player.getPosition() > highestScore){
                highestScore = player.getPosition();
                winningPlayer = player;
            }
        }
        return winningPlayer;
    }


}
