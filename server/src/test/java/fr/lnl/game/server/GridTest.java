package fr.lnl.game.server;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.action.*;
import fr.lnl.game.server.games.grid.EnergyBall;
import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.grid.Wall;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Cardinal;
import fr.lnl.game.server.utils.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GridTest {

    private Grid grid;
    private Game game;

    @BeforeEach
    public void mock() {
        Mock mock = new Mock();
        grid = mock.grid;
        game = mock.game;
    }

    @Test
    public void testGrid() {
        // test Grid#initGrid()
        assertEquals(new Wall(Cardinal.NORTH_WEST, 0, 0), grid.getBoard().get(new Point(0,0)).getB());
        assertEquals(new Wall(Cardinal.NORTH_EAST, 0, grid.getColumn() - 1), grid.getBoard().get(new Point(0, grid.getColumn() - 1)).getB());
        assertEquals(new Wall(Cardinal.SOUTH_WEST, grid.getRow() - 1, 0), grid.getBoard().get(new Point(grid.getRow() - 1, 0)).getB());
        assertEquals(new Wall(Cardinal.SOUTH_EAST, grid.getRow() - 1, grid.getColumn() - 1), grid.getBoard().get(new Point(grid.getRow() - 1, grid.getColumn() - 1)).getB());
        // test placePlayersBRUT (mocked)
        assertEquals(grid.getPlayers().get(0), grid.getBoard().get(new Point(7, 7)).getA());
        assertEquals(grid.getPlayers().get(1), grid.getBoard().get(new Point(7, 8)).getA());
        // test placeEnergyBallBRUT (mocked)
        assertEquals(new EnergyBall(), grid.getBoard().get(new Point(2, 3)).getB());
        assertEquals(new EnergyBall(), grid.getBoard().get(new Point(8, 10)).getB());
    }

    @Test
    public void testPlay(){
        while (!game.isOver()){
            System.out.println(" Tour du joueur " + game.getCurrentPlayer().getId() + " : " +
                    game.getCurrentPlayer().getEnergy() + " points de vies restants");
            Player player = game.getCurrentPlayer();
            ArrayList<Action> actions = new ArrayList<>();
            for(Move.Direction direction : Move.Direction.values()) {
                try {
                    actions.add(new Move(game, player, direction));
                } catch (NotValidDirectionException ignored){}
            }
            actions.addAll(Arrays.asList(new Nothing(), new Shot(game, player),
                    new DeployShield(player), new DropBomb(game, player), new DropMine(game, player)));
            player.setActions(actions);
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
        System.out.println("Le joueur gagnant : " + game.getWinner().getId());
    }

}
