package com.cm6123.wormhole;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import com.cm6123.wormhole.game.GameBoard;



public class BoardChecks {

    @Test
    public void shouldBeAbleToCreateBoardOf5 (){
        GameBoard newBoard = new GameBoard(5);
        assertEquals(newBoard.getBoardSize(), 25);
    }
}
