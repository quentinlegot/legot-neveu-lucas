package fr.lnl.game.server.games;

import fr.lnl.game.server.Mock;
import fr.lnl.game.server.games.action.Action;
import fr.lnl.game.server.games.action.Nothing;
import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.player.ClassPlayer;
import fr.lnl.game.server.games.player.HumanPlayer;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.games.player.RandomComputerPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameTest {

    private Grid grid;
    private Game game;

    @BeforeEach
    public void mock() {
        List<Player> players = Arrays.asList(new HumanPlayer(1,null, ClassPlayer.DEFAULT),
                new HumanPlayer(2,null, ClassPlayer.DEFAULT));
        Mock mock = new Mock(players);
        grid = mock.grid;
        game = mock.game;
    }

    @Test
    public void testPlay(){
        System.out.println(game.getGrid());
        while (!game.isOver()){
            game.play();
        }
        System.out.println("Le gagnant est " + game.getWinner().toString());
    }
}
