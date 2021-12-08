package fr.lnl.game.server;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.grid.elements.Box;
import fr.lnl.game.server.games.grid.elements.EnergyBall;
import fr.lnl.game.server.games.grid.elements.Wall;
import fr.lnl.game.server.games.player.ClassPlayer;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.games.player.RandomComputerPlayer;
import fr.lnl.game.server.mock.Mock;
import fr.lnl.game.server.utils.Pair;
import fr.lnl.game.server.utils.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GridTest {

    private Grid grid;
    private Game game;

    @BeforeEach
    public void mock() {
        List<Player> players = Arrays.asList(new RandomComputerPlayer(1,null, ClassPlayer.DEFAULT),
                new RandomComputerPlayer(2,null, ClassPlayer.DEFAULT));
        Mock mock = new Mock(players);
        grid = mock.grid;
        game = mock.game;
    }

    @Test
    public void testGrid() {
        // test Grid#initGrid()
        assertEquals(new Wall(), grid.getBoard().get(new Point(0,0)).getB());
        assertEquals(new Wall(), grid.getBoard().get(new Point(0, grid.getColumn() - 1)).getB());
        assertEquals(new Wall(), grid.getBoard().get(new Point(grid.getRow() - 1, 0)).getB());
        assertEquals(new Wall(), grid.getBoard().get(new Point(grid.getRow() - 1, grid.getColumn() - 1)).getB());
        // test placePlayersBRUT (mocked)
        grid.getPlayers().forEach(p -> System.out.println(p.getId() + ": " + p.getPosition()));
        assertEquals(grid.getPlayers().get(0), grid.getBoard().get(new Point(7, 7)).getA());
        assertEquals(grid.getPlayers().get(1), grid.getBoard().get(new Point(7, 8)).getA());
        // test placeEnergyBallBRUT (mocked)
        assertEquals(new EnergyBall(), grid.getBoard().get(new Point(2, 3)).getB());
        assertEquals(new EnergyBall(), grid.getBoard().get(new Point(8, 10)).getB());
    }

    @Test
    public void testLock() {
        System.out.println("=================== GRID COMPLETE ====================");
        System.out.println(grid.toString());
        System.out.println("=================== GRID AVEC LOCK ===================");
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < grid.getRow(); i++) {
            str.append("\n");
            for (int j = 0; j < grid.getColumn(); j++) {
                Pair<Player, Box> value = grid.getBoard().get(new Point(i, j));
                if(value.getB() != null){
                    if(value.getB().isLock()){
                        str.append(" \033[0;35mL\033[0m");
                    }
                    if(value.getB() instanceof Wall){
                        str.append(" \033[0;32m□\033[0m");
                    }
                    if(value.getB() instanceof EnergyBall){
                        str.append(" \033[0;31mE\033[0m");
                    }
                }
                else if(value.getA() != null){
                    str.append(" \033[0;34mP\033[0m");
                }
                else {
                    str.append(" \033[0;37m.\033[0m");
                }
            }
        }
        System.out.println(str);
    }
}
