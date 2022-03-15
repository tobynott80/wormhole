package com.cm6123.wormhole.board;


public class Wormhole {
    /**
     * Location of individual wormhole object.
     */
    protected final int position;

    /**
     * Constructor of wormhole object.
     * @param pos Position of wormhole
     */
    public Wormhole(final int pos) {
        this.position =  pos;
    }


    /**
     * @return Returns postion of the wormhole
     */
    public Integer getPosition() {
        return position;
    }


}