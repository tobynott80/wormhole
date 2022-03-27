package com.cm6123.wormhole.board;


public class Wormhole {
    /**
     * Location of individual wormhole object.
     */
    private final int position;

    /**
     * Constructor of wormhole object.
     * @param pos Position of wormhole
     */
    protected Wormhole(final int pos) {
        this.position =  pos;
    }


    /**
     * @return Returns postion of the wormhole
     */
    public Integer getPosition() {
        return position;
    }


}
