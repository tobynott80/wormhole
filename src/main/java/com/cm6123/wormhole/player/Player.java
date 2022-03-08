package com.cm6123.wormhole.player;

public class Player {

    private int position;

    public Player(int startingPosition) {
        position = startingPosition;
    }

    public Player(){
        position = 1;
    }

    public int getPosition() {
        return position;
    }

}
