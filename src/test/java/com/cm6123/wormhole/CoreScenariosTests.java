package com.cm6123.wormhole;

import com.cm6123.wormhole.board.GameBoard;
import com.cm6123.wormhole.player.Player;
import com.cm6123.wormhole.player.PlayerController;
import org.junit.jupiter.api.Test;

public class CoreScenariosTests {

    @Test
    private void shouldBeAbleToCompleteTest1(){
        /**
         * Given:
         *   A board is being played on a boardof size 4 with no wormholes and all players are on square 1 and it is player 1’s turn
         *
         * When:
         *  A 3 and 4 are rolled
         * Then:
         *  Player 1 ends on square 7 and it is player 2’s turn
         */
        GameBoard gb = new GameBoard(4); //initialise new gameboard of width
        PlayerController controller = new PlayerController(2);

        Player player1 = new Player(1);

    }
}
