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
        assertEquals(grid.getPlayers().get(0), grid.getBoard().get(new Point(1, 1)).getA());
        assertEquals(grid.getPlayers().get(1), grid.getBoard().get(new Point(14, 14)).getA());
        // test placeEnergyBallBRUT (mocked)
        assertEquals(new EnergyBall(), grid.getBoard().get(new Point(2, 3)).getB());
        assertEquals(new EnergyBall(), grid.getBoard().get(new Point(7, 10)).getB());
    }


    @Test
    public void testPlay(){
        for (Player player: game.getPlayers()) {
            player.setActions(new Action[]{new Nothing(game), new Shot(game), new Move(game),
                    new DeployShield(game), new DropBomb(game), new DropMine(game)});
        }
        while (!game.isOver()){
            System.out.println(" Tour du joueur " + game.getCurrentPlayer().getId() + " : " +
                    game.getCurrentPlayer().getEnergy() + " points de vies restants");
            System.out.println(game.getGrid().toString());
            Action action = game.getCurrentPlayer().choseAction();
            action.doAction();
            System.out.println("Action " + action + " : " + game.getCurrentPlayer().getEnergy() +
                    " points de vies restants");
            game.nextCurrentPlayer();
        }
        System.out.println(game.getGrid().toString());
        System.out.println("Le joueur gagnant : " + game.getWinner().getId());
    }

}
