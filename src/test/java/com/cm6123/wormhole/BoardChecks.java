package com.cm6123.wormhole;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.cm6123.wormhole.board.GameBoard;


public class BoardChecks {

    @Test
    public void shouldBeAbleToCreateBoardOf5() {
        GameBoard newBoard = new GameBoard(5);
        assertEquals(newBoard.getBoardSize(), 25);
    }

    @Test
    public void shouldBeAbleToCreateBoardOf10() {
        GameBoard newBoard = new GameBoard(10);
        assertEquals(newBoard.getBoardSize(), 100);
    }

    @Test
    public void shouldNotCreateOutofBounds() {
        assertThrows(ArithmeticException.class, () -> {
            GameBoard newBoard = new GameBoard(1);
        }, "Board width must be between 3 and 10");
        assertThrows(ArithmeticException.class, () -> {
            GameBoard newBoard = new GameBoard(11);
        }, "Board width must be between 3 and 10");

    }

}
