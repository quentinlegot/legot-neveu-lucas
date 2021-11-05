package fr.lnl.game.server;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.action.Action;
import fr.lnl.game.server.games.action.Move;
import fr.lnl.game.server.games.action.NotValidDirectionException;
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
        Action move = null;
        Point oldPoint = game.getCurrentPlayer().getPoint();
        Move.Direction savedDirection = null;
        for(Move.Direction direction : Move.Direction.values()) {
            try {
                move = new Move(game, game.getCurrentPlayer(), direction);
                savedDirection = direction;
                break;
            } catch (NotValidDirectionException ignored) {}
        }
        Assertions.assertNotEquals(null, move);
        assert move != null;
        move.doAction();
        Point newPoint = game.getCurrentPlayer().getPoint();
        Assertions.assertEquals(newPoint,
                new Point(oldPoint.getA() + savedDirection.getDeltaX(),
                        oldPoint.getA() + savedDirection.getDeltaY()
                )
        );
    }


    // TODO: 10/28/2021 pas un vrai test et marche qu'avec le mock actuel
    @Test
    public void shotActionTest(){
        System.out.println(grid.toString());
        Shot shot = new Shot(game, game.getCurrentPlayer());
        List<Point> points = shot.getValidPoint();
        System.out.println(points);
        System.out.println("Before shot " + game.getPlayers().get(1).getEnergy());
        shot.doAction();
        System.out.println("After shot " + game.getPlayers().get(1).getEnergy());
    }

}
