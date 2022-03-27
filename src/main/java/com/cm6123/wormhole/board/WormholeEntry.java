package com.cm6123.wormhole.board;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Subclass for an entry wormhole.
 */
public final class WormholeEntry extends Wormhole {
    /**
     * Polarity of entry - either positive or negative.
     */
    private final WormholeType polarity;

    /**
     * @param polar Polarity - either positive or negative.
     * @param pos Postion of wormhole on board.
     */
    public WormholeEntry(final WormholeType polar, final Integer pos) {
        super(pos);
        this.polarity = polar;
    }

    /**
     * @return Returns the polarity of entry wormhole.
     */
    public WormholeType getPolarity() {
        return polarity;
    }

    /**
     * Returns the nearest exit wormhole, either higher or lower than
     * the entry wormhole, depending on its polarity.
     * @return The nearest exit wormhole integer
     */
    public int getExit() {
        ArrayList<WormholeExit> exitWormholeList  = GameBoard.getExitWormholeList();
        Collections.shuffle(exitWormholeList);
        if (this.polarity == WormholeType.positive) {
            for (WormholeExit exit : exitWormholeList) {
                if (exit.getPosition() > super.getPosition()) {
                    return exit.getPosition();
                }
            }
        } else {
            for (WormholeExit exit : exitWormholeList) {
                if (exit.getPosition() < super.getPosition()) {
                    return exit.getPosition();
                }
            }
        }
        return super.getPosition();


    }
}
