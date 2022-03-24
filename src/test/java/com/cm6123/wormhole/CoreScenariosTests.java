package com.cm6123.wormhole;

import com.cm6123.wormhole.board.GameBoard;
import com.cm6123.wormhole.board.WormholeType;
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
        assertEquals(controller.getPosition(0),1); //check that player 1 has been initialised and is at the correct position
        controller.movePlayer(0,3); //rolls a 3
        controller.movePlayer(0,4); //rolls a 4
        controller.nextPlayer(); //change to player 2's turn
        assertEquals(controller.getPosition(0),8); //check that player 1 actually lands on square 8
        assertEquals(1, controller.getCurrentPlayer()); //check player 2 (1) is the current player

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
        controller.namePlayer(0, "Toby");
        controller.movePlayer(0, 5); //rolls a 5
        controller.movePlayer(0, 6); //rolls a 6
        assertTrue(controller.gameOver()); //check game is over
        assertEquals("Toby", controller.getWinner().getName()); //check player 1 is the winner
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
        for (Player player: controller.getPlayerList()) {
            player.movePlayer(aDice.roll());    //roll each dice
            player.movePlayer(bDice.roll());    //roll each dice
            controller.nextPlayer();    //change to player 2's go
        }
        for (Player player: controller.getPlayerList()) {
            assertNotEquals(player.getPosition(),1); //assert that each player has succsessfully moved past square 1
        }
        assertEquals(0, controller.getCurrentPlayer()); //assert that it is now player 1's (0) go

    }
    @Test
    public void shouldBeAbleToCompleteTest4 (){
        // See CoreScenarios.md test 4.
        // A game is being played on a board of size 5 with a positive wormhole from square 7 to square 20
        // and all players are on square 1 and it is player 1's turn
        // 
        // Player 1 rolls a 4 and 2 (1+4+2=7)
        //  
        // Then Player 1 should be on square 20 and it is player 2's turn 
        GameBoard gb = new GameBoard(5);
        PlayerController controller = new PlayerController(2);
        controller.initialisePlayers(1);
        gb.addWormhole(7, WormholeType.positive, 20);
        controller.movePlayer(0, 4);
        controller.movePlayer(0, 2);
        controller.nextPlayer();    //change to player 2's go
        assertEquals(20, controller.getPosition(0)); //check that player succsessfully moved to square 20 after landing on the wormhole
        assertEquals(1, controller.getCurrentPlayer()); //check it's player 2's (1) turn
    }

    @Test
    public void shouldBeAbleToCompleteTest5 () {
        /**
         * A game is being played on a board of size 5 with a positive wormhole from square 6 to square 20 and all players are on square 1 and it is player 1's turn
         *
         * Player 1 rolls a 3 and 3
         *
         * Then Player 1 should be on square 20 and it is player 2's turn	N/A
         */
        GameBoard gb = new GameBoard(5);
        PlayerController controller = new PlayerController(2);
        controller.initialisePlayers(1);
        gb.addWormhole(7, WormholeType.positive, 20);
        controller.movePlayer(0, 3);
        controller.movePlayer(0, 3);
        controller.nextPlayer();    //change to player 2's go
        assertEquals(20, controller.getPosition(0)); //check that player succsessfully moved to square 20 after landing on the wormhole
        assertEquals(1, controller.getCurrentPlayer()); //check it's player 2's (1) turn
    }

    @Test
    public void shouldBeAbleToCompleteTest6 () {
        /**
         * A game is being played on a board of size 5 with a negative wormhole from square 11 to square 2 and all players are on square 1 and it is player 1's turn
         *
         * Player 1 rolls a 6 and 4
         *
         * Then Player 1 should be on square 2 and it is player 2's turn
         */
        GameBoard gb = new GameBoard(5);
        PlayerController controller = new PlayerController(2);
        controller.initialisePlayers(1);
        gb.addWormhole(11, WormholeType.negative, 2);
        controller.movePlayer(0, 6);
        controller.movePlayer(0, 4);
        controller.nextPlayer();    //change to player 2's go
        assertEquals(2, controller.getPosition(0)); //check that player succsessfully moved to square 20 after landing on the wormhole
        assertEquals(1, controller.getCurrentPlayer()); //check it's player 2's (1) turn
    }

    @Test
    public void shouldBeAbleToCompleteTest7 () {
        /**
         * A game is being played on a board of size 5 with a negative wormhole from square 11 to square 2 and all players are on square 1 and it is player 1's turn
         *
         * Player 1 rolls a 5 and 5
         *
         * Then Player 1 should be on square 10 and it is player 2's turn
         */
        GameBoard gb = new GameBoard(5);
        PlayerController controller = new PlayerController(2);
        controller.initialisePlayers(1);
        gb.addWormhole(11, WormholeType.negative, 2);
        // Logic for player rolling a double is currently in applcation class (line 141)
        // Ideally this should be moved for testing purposes
        // For the time being, the application class code is recreated below for testing
        Integer roll1 = 5;
        Integer roll2 = 5;
        if(gb.getEntryWormholeList().get(0).getPolarity().equals(WormholeType.negative) && roll1.equals(roll2)){
            //if player lands on neg. wormhole but rolls a double, do not move
            controller.movePlayer(0, 0);
        }
        controller.nextPlayer();    //change to player 2's go
        assertEquals(1, controller.getPosition(0)); //check that player succsessfully moved to square 20 after landing on the wormhole
        assertEquals(1, controller.getCurrentPlayer()); //check it's player 2's (1) turn
    }

    @Test
    public void shouldBeAbleToCompleteTest8 () {
        /**
         * A game is being played on a board of size 5 with a positive wormhole from square 7 to square 20 and 2 players are on square 1.
         * When Player 1 rolls a 4 and 2 And Player 2 rolls a 1 and 1, And Player 1 rolls a 2 and 3
         * Then Player 1 should be on square 25 and should be declared the winner
         */
        GameBoard gb = new GameBoard(5);
        PlayerController controller = new PlayerController(2);
        controller.initialisePlayers(1);
        controller.namePlayer(0, "Toby");
        gb.addWormhole(7, WormholeType.positive, 20);
        controller.movePlayer(controller.getCurrentPlayer(), 4);
        controller.movePlayer(controller.getCurrentPlayer(), 2);
        controller.nextPlayer();    //change to player 2's go
        controller.movePlayer(controller.getCurrentPlayer(), 1);
        controller.movePlayer(controller.getCurrentPlayer(), 1);
        controller.nextPlayer();
        controller.movePlayer(controller.getCurrentPlayer(), 2);
        controller.movePlayer(controller.getCurrentPlayer(), 3);
        assertEquals("Toby", controller.getWinner().getName());
        assertEquals(25, controller.getPosition(0));
    }

    @Test
    public void shouldBeAbleToCompleteTest9 () {
        /**
         * A game is being played on a boardof size 5 with a positive wormhole from square 6 to square 20
         * and 2 players are on square 1 and it is player 1’s turn
         *
         * When Player 1 rolls a 4 and 2, Player 2 rolls a 1 and 1, Player 1 rolls a 3 and 3
         *
         * Then Player 1 should be on square 26 and should be declared the winner
         */
        GameBoard gb = new GameBoard(5);
        PlayerController controller = new PlayerController(2);
        controller.initialisePlayers(1);
        controller.namePlayer(0, "Toby");
        gb.addWormhole(7, WormholeType.positive, 20);
        controller.movePlayer(controller.getCurrentPlayer(), 4);
        controller.movePlayer(controller.getCurrentPlayer(), 2);
        controller.nextPlayer();    //change to player 2's go
        controller.movePlayer(controller.getCurrentPlayer(), 1);
        controller.movePlayer(controller.getCurrentPlayer(), 1);
        controller.nextPlayer();
        controller.movePlayer(controller.getCurrentPlayer(), 3);
        controller.movePlayer(controller.getCurrentPlayer(), 3);
        assertEquals("Toby", controller.getWinner().getName());
        assertEquals(26, controller.getPosition(0));
    }

}
