package fr.lnl.game.server;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.action.Shot;
import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.utils.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ActionPlayerTest {

    private Grid grid;
    private Game game;

    @BeforeEach
    public void mock() {
        Mock mock = new Mock();
        this.grid = mock.grid;
        this.game = mock.game;
    }

    // TODO: 21/10/2021 Vérifier sur Move effectue la bonne action en pensant a appeler isPossible() avant et
    //  en checkant son résultat
    @Test
    public void moveActionTest() {
        Assertions.assertEquals(game.getPlayers().get(0), game.getCurrentPlayer());
    }


    // TODO: 10/28/2021 pas un vrai test et marche qu'avec le mock actuel
    @Test
    public void shotActionTest(){
        System.out.println(grid.toString());
        Shot shot = new Shot(game);
        List<Point> points = shot.getValidPoint();
        System.out.println(points);
        System.out.println("Before shot " + game.getPlayers().get(1).getEnergy());
        shot.doAction();
        System.out.println("After shot " + game.getPlayers().get(1).getEnergy());
    }

}
