package com.cm6123.wormhole.board;

import java.util.Random;

/**
 * Enum for polarity of entry wormhole- either positive or negative, depending on direction the player is being sent.
 */
public enum WormholeType {
    /**
     * Positive polarity - sends the player forwards.
     */
    positive,
    /**
     * Negative polarity - sends the player backwards.
     */
    negative;

    /**
     * Function to return random polarity from the Enum.
     * @return A random polarity (positive or negative)
     */
    public static WormholeType getRandomPolarity() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];

    }
}
