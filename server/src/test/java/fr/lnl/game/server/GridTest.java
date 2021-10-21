package fr.lnl.game.server;

import fr.lnl.game.server.games.grid.EnergyBall;
import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.grid.Wall;
import fr.lnl.game.server.games.player.ComputerPlayer;
import fr.lnl.game.server.games.player.DefaultClassPlayer;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Cardinal;
import fr.lnl.game.server.utils.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest {

    private Grid grid;

    @Test
    public void testGrid() {
        Player playerOne = new ComputerPlayer(1,null, new DefaultClassPlayer());
        Player playerTwo = new ComputerPlayer(2,null, new DefaultClassPlayer());
        this.grid = new Grid(16,16,new Player[]{playerOne,playerTwo});
        grid.initGrid();
        placePlayersBRUT();
        placeEnergyBallBRUT();
        placeInternWallBRUT();
        // test Grid#initGrid()
        assertEquals(new Wall(Cardinal.NORTH_WEST, 0, 0), grid.getBoard().get(new Point(0,0)).getB());
        assertEquals(new Wall(Cardinal.NORTH_EAST, 0, grid.getColumn() - 1), grid.getBoard().get(new Point(0, grid.getColumn() - 1)).getB());
        assertEquals(new Wall(Cardinal.SOUTH_WEST, grid.getRow() - 1, 0), grid.getBoard().get(new Point(grid.getRow() - 1, 0)).getB());
        assertEquals(new Wall(Cardinal.SOUTH_EAST, grid.getRow() - 1, grid.getColumn() - 1), grid.getBoard().get(new Point(grid.getRow() - 1, grid.getColumn() - 1)).getB());
        // test placePlayersBRUT (mocked)
        assertEquals(grid.getPlayers()[0], grid.getBoard().get(new Point(1, 1)).getA());
        assertEquals(grid.getPlayers()[1], grid.getBoard().get(new Point(14, 14)).getA());
        // test placeEnergyBallBRUT (mocked)
        assertEquals(new EnergyBall(), grid.getBoard().get(new Point(2, 3)).getB());
        assertEquals(new EnergyBall(), grid.getBoard().get(new Point(7, 10)).getB());
    }

    public void placePlayersBRUT(){
        grid.getBoard().get(new Point(1,1)).setA(grid.getPlayers()[0]);
        grid.getBoard().get(new Point(14,14)).setA(grid.getPlayers()[1]);
    }

    public void placeEnergyBallBRUT(){
        grid.getBoard().get(new Point(2,3)).setB(new EnergyBall());
        grid.getBoard().get(new Point(7,10)).setB(new EnergyBall());
    }

    public void placeInternWallBRUT(){
        grid.getBoard().get(new Point(3,6)).setB(new Wall(Cardinal.NORTH,3,6));
        grid.getBoard().get(new Point(7,14)).setB(new Wall(Cardinal.SOUTH,7,14));
        grid.getBoard().get(new Point(10,7)).setB(new Wall(Cardinal.EAST,10,7));
        grid.getBoard().get(new Point(14,2)).setB(new Wall(Cardinal.WEST,14,2));
    }

}
