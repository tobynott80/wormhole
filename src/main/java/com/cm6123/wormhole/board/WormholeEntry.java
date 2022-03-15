package com.cm6123.wormhole.board;

import java.util.Collections;

import static com.cm6123.wormhole.board.GameBoard.exitWormholeList;

/**
 * Subclass for an entry wormhole.
 */
public final class WormholeEntry extends Wormhole {
    /**
     * Polarity of entry - either positive or negative.
     */
    private final WormholeType polarity;
    /**
     * Postion of wormhole.
     */
    private final Integer position;

    /**
     * @param polar Polarity - either positive or negative.
     * @param pos Postion of wormhole on board.
     */
    public WormholeEntry(final WormholeType polar, final Integer pos) {
        super(pos);
        this.polarity = polar;
        this.position = pos;
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
        Collections.shuffle(exitWormholeList);
        if (this.polarity == WormholeType.positive) {
            for (WormholeExit exit : exitWormholeList) {
                if (exit.getPosition() > position) {
                    return exit.getPosition();
                }
            }
        } else {
            for (WormholeExit exit : exitWormholeList) {
                if (exit.getPosition() < position) {
                    return exit.getPosition();
                }
            }
        }
        return position;


    }
}
