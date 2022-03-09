package com.cm6123.wormhole;

import com.cm6123.wormhole.player.PlayerController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerSuite {
    @Test
    public void shouldCreate2PlayersWithStartingPosition1(){
        PlayerController controller = new PlayerController(2);
        controller.initialisePlayers(1);
        assertEquals(controller.playerList.get(0).getPosition(),1);
        assertEquals(controller.playerList.get(1).getPosition(),1);

    }

    @Test
    public void shouldMovePlayer1butNotPlayer2(){
        PlayerController controller = new PlayerController(2);
        controller.initialisePlayers(1);
        controller.playerList.get(0).movePlayer(5); //move player 1 by 5 spaced, new position should be 6
        assertEquals(controller.playerList.get(0).getPosition(),6);
        assertEquals(controller.playerList.get(1).getPosition(),1);

    }
}