package com.cm6123.wormhole;

import com.cm6123.wormhole.board.GameBoard;
import com.cm6123.wormhole.board.WormholeType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class WormholeChecks {



    @Test
    public void shouldPrintEntryHoles(){
        GameBoard gbd = new GameBoard(5);
        gbd.addWormhole(5, WormholeType.positive,8);
        assertEquals(gbd.getEntryHoles(),"5 (positive), ");

    }

    @Test
    public void shouldPrintExitHoles(){
        GameBoard gbd = new GameBoard(5);
        gbd.addWormhole(5, WormholeType.positive,8);
        assertEquals(gbd.getExitHoles(),"8, ");

    }
}
