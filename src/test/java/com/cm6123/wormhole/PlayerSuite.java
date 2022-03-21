package com.cm6123.wormhole;

import com.cm6123.wormhole.board.GameBoard;
import com.cm6123.wormhole.board.WormholeType;
import com.cm6123.wormhole.player.Player;
import com.cm6123.wormhole.player.PlayerController;
import org.apache.logging.log4j.core.util.Assert;
import org.junit.jupiter.api.Test;

import static com.cm6123.wormhole.dice.DiceMode.automatic;
import static com.cm6123.wormhole.dice.DiceMode.manual;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerSuite {
    @Test
    public void shouldCreatePlayer() {
        Player aPlayer = new Player();
        assertEquals(aPlayer.getPosition(), 1);
    }


    @Test
    public void shouldCreatePlayerStartingAt10() {
        Player aPlayer = new Player(10);
        assertEquals(aPlayer.getPosition(), 10);
    }


    @Test
    public void shouldCreate2PlayersWithStartingPosition1() {
        PlayerController controller = new PlayerController(2);
        controller.initialisePlayers(1);
        assertEquals(controller.getPosition(0), 1);
        assertEquals(controller.getPosition(1), 1);

    }

    @Test
    public void shouldMovePlayer1butNotPlayer2() {
        PlayerController controller = new PlayerController(2);
        controller.initialisePlayers(1);
        controller.movePlayer(0,5); //move player 1 by 5 spaced, new position should be 6
        assertEquals(controller.getPosition(0), 6);
        assertEquals(controller.getPosition(1), 1);

    }

    @Test
    public void shouldBeGameOver() {
        GameBoard gb = new GameBoard(5);
        PlayerController controller = new PlayerController(2);
        controller.initialisePlayers(1);
        controller.movePlayer(0,24);  //moving player to the exact fininishing location 1+24=25
        assertTrue(controller.gameOver());

        gb = new GameBoard(5);
        controller = new PlayerController(2);
        controller.initialisePlayers(1);
        controller.movePlayer(0,999);
        assertTrue(controller.gameOver());

        gb = new GameBoard(5);
        controller = new PlayerController(2);
        controller.initialisePlayers(1);
        controller.movePlayer(0,23);
        assertFalse(controller.gameOver());
    }

    @Test
    public void shouldNotBeGameOver() {
        GameBoard gb = new GameBoard(5);
        PlayerController controller = new PlayerController(2);
        controller.initialisePlayers(1);
        assertFalse(controller.gameOver());
        controller.movePlayer(0,1);
        assertFalse(controller.gameOver());
        controller.movePlayer(1,5);
        assertFalse(controller.gameOver());
        controller.movePlayer(0,-2);
        assertFalse(controller.gameOver());
    }

    @Test
    public void shouldBeOnAWormhole() {
        GameBoard gb = new GameBoard(5);
        PlayerController controller = new PlayerController(2);
        controller.initialisePlayers(1);
        gb.addWormhole(3, WormholeType.positive, 8); //exit must be on 3 otherwise player will no longer be on a wormhole
        assertTrue(controller.checkWormholes(3)); //3 should retunrn true because it is a wormhole
        assertFalse(controller.checkWormholes(8)); //8 should be false because even though it is a wormhole, it is an exit wormhole, so nothing happens if a player lands on it
        assertFalse(controller.checkWormholes(12)); //12 is not a wormhole
        assertFalse(controller.checkWormholes(98)); //98 is not a valid location on the board

    }

    @Test
    public void shouldBeAbleToNamePlayer() {
        GameBoard gb = new GameBoard(5);
        PlayerController controller = new PlayerController(2);
        controller.initialisePlayers(1);
        controller.namePlayer(0, "Toby");
        assertEquals("Toby", controller.getName(0));
    }

    @Test
    public void shouldBeAbleToAssignDiceRollMethod() {
        GameBoard gb = new GameBoard(5);
        PlayerController controller = new PlayerController(2);
        controller.initialisePlayers(1);
        controller.namePlayer(0, "Toby");
        controller.diceMode(0, automatic);
        assertEquals(automatic, controller.getDice(0));

        controller.namePlayer(0, "Jeremy");
        controller.diceMode(0, manual);
        assertEquals(manual, controller.getDice(0));
    }

    @Test
    public void shouldFindWinner() {
        GameBoard gb = new GameBoard(5);
        PlayerController controller = new PlayerController(2);
        controller.initialisePlayers(1);
        controller.namePlayer(0, "Toby");
        controller.movePlayer(0, 28);
        assertEquals("Toby", controller.getWinner().getName());
    }

    @Test
    public void shouldBeAbleToGetNextPlayer() {
        GameBoard gb = new GameBoard(5);
        PlayerController controller = new PlayerController(2);
        controller.initialisePlayers(1);
        controller.namePlayer(0, "Toby");
        assertEquals(0, controller.getCurrentPlayer());
        controller.nextPlayer();
        assertEquals(1, controller.getCurrentPlayer());
        controller.nextPlayer();
        assertEquals(0, controller.getCurrentPlayer());
    }
}

