package com.cm6123.wormhole;

import com.cm6123.wormhole.board.GameBoard;
import com.cm6123.wormhole.board.WormholeEntry;
import com.cm6123.wormhole.board.WormholeType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class WormholeChecks {

    @Test
    public void shouldBeAbleToInitialiseWormholes(){
        GameBoard gbd = new GameBoard(5);
        gbd.initialiseWormholes();
        assertEquals(5, gbd.getEntryWormholeList().size());  //ensure 5 entry wormholes have been created
        assertEquals(5, gbd.getExitWormholeList().size()); //ensure 5 exit wormholes have been created

    }

    @Test
    public void shouldBeAbleToReturnAvailablePostitions(){
        GameBoard gbd = new GameBoard(5);
        ArrayList<Integer> generated = gbd.getAvailablePositions();
        assertEquals(2, generated.get(0)); //cannot have wormholes on first square, so first available wormhole should be on square 2
        assertEquals(24, generated.get(generated.size()-1)); //cannot have wormholes on last square, so last available wormhole should be on square 24
        assertEquals(15, generated.get(13)); //checking that 13th item in list is correct
    }

    /**
     * Same as test above, but for larger board
     */
    @Test
    public void shouldBeAbleToReturnAvailablePostitions2(){
        GameBoard gbd = new GameBoard(10);
        ArrayList<Integer> generated = gbd.getAvailablePositions();
        assertEquals(2, generated.get(0)); //cannot have wormholes on first square, so first available wormhole should be on square 2
        assertEquals(99, generated.get(generated.size()-1)); //cannot have wormholes on last square, so last available wormhole should be on square 99
        assertEquals(15, generated.get(13)); //checking that 13th item in list is correct
    }

    @Test
    public void shouldBeAbleToFindNearestExit(){
        GameBoard gbd = new GameBoard(5);
        ArrayList<WormholeEntry> entryWormholeList = GameBoard.getEntryWormholeList();
        gbd.addWormhole(5, WormholeType.positive,8);
        assertEquals(8, entryWormholeList.get(0).getExit());
        gbd.addWormhole(22, WormholeType.negative,8);
        assertEquals(8, entryWormholeList.get(1).getExit());
        gbd.addWormhole(22, WormholeType.positive,8);
        assertEquals(22, entryWormholeList.get(2).getExit());

    }

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
