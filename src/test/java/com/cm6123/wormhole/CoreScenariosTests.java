package com.cm6123.wormhole;

import com.cm6123.wormhole.board.GameBoard;
import com.cm6123.wormhole.dice.Dice;
import com.cm6123.wormhole.player.Player;
import com.cm6123.wormhole.player.PlayerController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoreScenariosTests {

    @Test
    public void shouldBeAbleToCompleteTest1() {
         //
         // Given:
         //   A board is being played on a boardof size 4 with no wormholes
         //   and all players are on square 1 and it is player 1’s turn
         //
         // When:
         //  A 3 and 4 are rolled
         // Then:
         //  Player 1 ends on square 8 and it is player 2’s turn

        GameBoard gb = new GameBoard(4); //initialise new gameboard of width 4
        PlayerController controller = new PlayerController(2); //create new 2 player controller
        controller.initialisePlayers(1); //initialise players starting at 1
        assertEquals(controller.playerList.get(0).getPosition(),1); //check that player 1 has been initialised and is at the correct position
        controller.playerList.get(0).movePlayer(3); //rolls a 3
        controller.playerList.get(0).movePlayer(4); //rolls a 4
        assertEquals(controller.playerList.get(0).getPosition(),8); //check that player 1 actually lands on square 8

    }

    @Test
    public void shouldBeAbleToCompleteTest2 (){
        // given:
        // A game is being played on a board
        // of size 3 with no wormholes and
        // all players are on square 1 and it
        // is player 1’s turn
        //
        // When:
        //  A 5 and 6 are rolled
        //
        // Then:
        // Player 1 is on square 9 and is declared the winner.

        GameBoard gb = new GameBoard(3); //initialise new gameboard of width 3
        PlayerController controller = new PlayerController(2); //create new 2 player controller
        controller.initialisePlayers(1); //initialise players starting at 1
        controller.playerList.get(0).movePlayer(5); //rolls a 5
        controller.playerList.get(0).movePlayer(6); //rolls a 6
        assertTrue(controller.gameOver(9)); //checks if player has won
    }

    @Test
    public void shouldBeAbleToCompleteTest3 (){
        //Given:
        //A game is being played on a board of size 5 with no wormholes and
        //all players are on square 1 and it is player 1’s turn.
        //
        //When:
        //When all players have rolled
        //
        //Then:
        //It is Player 1’s turn
        GameBoard gb = new GameBoard(5); //initialise new gameboard of width 5
        PlayerController controller = new PlayerController(2); //create new 2 player controller
        controller.initialisePlayers(1); //initialise players starting at 1
        Dice aDice = new Dice(6);   // create set of two dice
        Dice bDice = new Dice(6);   // create set of two dice
        for (Player player: controller.playerList) {
            player.movePlayer(aDice.roll());    //roll each dice
            player.movePlayer(bDice.roll());    //roll each dice
        }
        for (Player player: controller.playerList) {
            assertNotEquals(player.getPosition(),1); //assert that each player has succsessfully moved past square 1
        }

    }
    @Test
    public void shouldBeAbleToCompleteTest4 (){
        //See CoreScenarios.md test 4
        //A game is being played on a board of size 5 with a positive wormhole from square 6 to square 20
        // and all players are on square 1 and it is player 1's turn
        // 
        // Player 1 rolls a 4 and 2 
        //  
        // Then Player 1 should be on square 20 and it is player 2's turn 
        GameBoard gb = new GameBoard(5);
        PlayerController controller = new PlayerController(2);
        controller.initialisePlayers(1);
        gb.addPositiveWormhole(6,20);
        controller.playerList.get(0).movePlayer(4);
        controller.playerList.get(0).movePlayer(2);
        assertTrue(controller.checkWormholes(););
    }
}
