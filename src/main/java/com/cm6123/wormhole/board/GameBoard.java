package com.cm6123.wormhole.board;


import java.util.ArrayList;
import java.util.Collections;


/**
 * Class to manage the actions and methods of the gameboard.
 */
@SuppressWarnings("unchecked")
public final class GameBoard {

    /**
     * Integer to define the width of the board.
     */
    private static int boardWidth;

    /**
     * @return Returns the entry wormhole list
     */
    public static ArrayList<WormholeEntry> getEntryWormholeList() {
        return entryWormholeList;
    }

    /**
     * @return Returns the exit wormhole list.
     */
    public static ArrayList<WormholeExit> getExitWormholeList() {
        return exitWormholeList;
    }

    /**
     * ArrayList for holding the entry wormhole objects.
     */
    private static ArrayList<WormholeEntry> entryWormholeList = new ArrayList<WormholeEntry>();
    /**
     * ArrayList for holding the exit wormhole objects.
     */
    private static ArrayList<WormholeExit> exitWormholeList = new ArrayList<WormholeExit>();

    /**
     * @return Returns this list of wormhole exits
     */
    public ArrayList<WormholeExit> getexitWormholeList(){
        return this.exitWormholeList;
    }


    /**
     * @param width with of the gameboard (between 5 and 10 in deployment).
     */
    public GameBoard(final int width) {
        this.boardWidth = width;
        this.entryWormholeList = new ArrayList<WormholeEntry>();
        this.exitWormholeList = new ArrayList<WormholeExit>();
    }

    /**
     * @return Returns int arraylist of available positions for placing wormholes
     */
    public ArrayList<Integer> getAvailablePositions() {
        ArrayList<Integer> availablePositions = new ArrayList<Integer>();
        for (int i = 2; i < (boardWidth * boardWidth); i++) {
            availablePositions.add(i);
        }
        return availablePositions;
    }

    /**
     * Void function to initialise wormhole lists.
     * Creates the correct no. of entry and exit wormholes at available postions.
     * Only to be run on boards of width 5-10
     */
    public void initialiseWormholes() {
        ArrayList<Integer> availablePositions = getAvailablePositions();
        Collections.shuffle(availablePositions);
        for (int i = 1; i < boardWidth + 1; i++) {
            entryWormholeList.add(new WormholeEntry(WormholeType.getRandomPolarity(), availablePositions.remove(0)));
            exitWormholeList.add(new WormholeExit(availablePositions.remove(0)));
        }
    }

    /**
     * @return the size of the board (no. of squares)
     */
    public static int getBoardSize() {
        return boardWidth * boardWidth;
    }


    /**
     * Testing method to create a new wormhole.
     * @param entry Postion of the entry wormhole
     * @param polarity Type of entry wormhole (positive/negative)
     * @param exit Postion of the exit wormhole
     */
    public void addWormhole(final int entry, final WormholeType polarity, final int exit) {
        entryWormholeList.add(new WormholeEntry(polarity, entry));
        exitWormholeList.add(new WormholeExit(exit));
        System.out.println("Entry wormhole(s) at " + entryWormholeList + ". Exit wormholes at " + exitWormholeList);
    }

    /**
     * @return Returns user-friendly print of current entry wormholes with polarities
     */
    public String getEntryHoles() {
        String returner = "";
        for (WormholeEntry wormhole: entryWormholeList) {
            returner = returner + wormhole.getPosition() + " (" + wormhole.getPolarity() + "), ";
        }
        return returner;
    }

    /**
     * @return Returns user-friendly print of current exit wormholes
     */
    public String getExitHoles() {
        String returner = "";
        for (WormholeExit wormhole: exitWormholeList) {
            returner = returner + wormhole.getPosition() + ", ";
        }
        return returner;
    }

    /**
     * Method to return requested wormhole object based off postion.
     * @param postion Position of entry hole
     * @return wormholeEntry object
     */
    public WormholeEntry getEntryHole(final int postion) {
        for (WormholeEntry wormhole: GameBoard.entryWormholeList){
            if (postion == wormhole.getPosition()){
                return wormhole;
            }
        }
        return null;
    }
}
