package fr.lnl.game.server.games;

import fr.lnl.game.server.Mock;
import fr.lnl.game.server.games.action.Action;
import fr.lnl.game.server.games.action.Nothing;
import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class GameTest {

    private Grid grid;
    private Game game;

    @BeforeEach
    public void mock() {
        Mock mock = new Mock();
        grid = mock.grid;
        game = mock.game;
    }

    @Test
    public void testPlay(){
        while (!game.isOver()){
            System.out.println(" Tour du joueur " + game.getCurrentPlayer().getId() + " : " +
                    game.getCurrentPlayer().getEnergy() + " points de vies restants");
            Player player = game.getCurrentPlayer();
            player.setActions(game.generateAndGetPlayerActions(player));
            System.out.println(game.getGrid().toString());
            Action action = null;
            switch (player.getActions().size()){
                case 0 -> action = new Nothing();
                case 1 -> action = game.getCurrentPlayer().getActions().get(0);
                default -> {
                    Random random = new Random();
                    while (action == null || !action.isPossible()) {
                        action = game.getCurrentPlayer().getActions().get(
                                random.nextInt(0,game.getCurrentPlayer().getActions().size())
                        );
                    }
                }
            }
            action.doAction();
            System.out.println("Action " + action + " : " + game.getCurrentPlayer().getEnergy() +
                    " points de vies restants");
            game.nextCurrentPlayer();
        }
        System.out.println(game.getGrid().toString());
        Player winner = game.getWinner();
        System.out.println(winner != null ? ("Le joueur gagnant : " + winner.getId()) : ("Partie nulle, aucun gagnant"));
    }
}
