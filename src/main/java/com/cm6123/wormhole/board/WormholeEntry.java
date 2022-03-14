package com.cm6123.wormhole.board;

import java.util.Collections;

import static com.cm6123.wormhole.board.GameBoard.exitWormholeList;

public final class WormholeEntry extends Wormhole {
    private final WormholeType polarity;
    private final Integer position;

    public WormholeEntry(final WormholeType polarity, final Integer position) {
        super(position);
        this.polarity = polarity;
        this.position = position;
    }

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
        if (this.polarity == WormholeType.positive){
            for (WormholeExit exit : exitWormholeList) {
                if (exit.getPosition() > position){
                    return exit.getPosition();
                }
            }
        } else{
            for (WormholeExit exit : exitWormholeList) {
                if (exit.getPosition() < position){
                    return exit.getPosition();
                }
            }
        }
        return position;


    }
}
