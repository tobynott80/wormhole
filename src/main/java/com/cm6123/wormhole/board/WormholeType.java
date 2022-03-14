package com.cm6123.wormhole.board;

import java.util.Random;

public enum WormholeType {
    positive, negative;

    /**
     * Function to return random polarity from the Enum
     * @return A random polarity (positive or negative)
     */
    public static WormholeType getRandomPolarity() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];

    }
}
