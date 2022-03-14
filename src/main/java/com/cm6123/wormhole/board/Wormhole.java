package com.cm6123.wormhole.board;

import java.util.Collections;

import static com.cm6123.wormhole.board.GameBoard.exitWormholeList;

public class Wormhole {
    protected final int position;

    public Wormhole(final int position) {
        this.position =  position;
    }


    public Integer getPosition(){
        return position;
    }


}
