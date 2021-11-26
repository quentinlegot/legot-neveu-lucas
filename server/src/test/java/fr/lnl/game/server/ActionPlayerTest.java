package fr.lnl.game.server;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.action.*;
import fr.lnl.game.server.games.grid.Bomb;
import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ActionPlayerTest {

    private Grid grid;
    private Game game;

    @BeforeEach
    public void mock() {
        Mock mock = new Mock();
        this.grid = mock.grid;
        this.game = mock.game;
        Assertions.assertEquals(game.getPlayers().get(0), game.getCurrentPlayer());
    }

    @Test
    public void moveActionTest() {
        Action move = null;
        Point oldPoint = game.getCurrentPlayer().getPoint();
        Direction savedDirection = null;
        for(Direction direction : Direction.values()) {
            try {
                move = new Move(game, game.getCurrentPlayer(), direction);
                savedDirection = direction;
                break;
            } catch (NotValidDirectionException ignored) {}
        }
        Assertions.assertNotNull(move);
        move.doAction();
        Point newPoint = game.getCurrentPlayer().getPoint();
        Assertions.assertEquals(newPoint,
                new Point(oldPoint.getA() + savedDirection.getDeltaX(),
                        oldPoint.getA() + savedDirection.getDeltaY()
                )
        );
    }

    @Test
    public void DeployShieldTest() {
        Player player = game.getCurrentPlayer();
        Assertions.assertFalse(player.isShieldDeploy());
        Action action = new DeployShield(player);
        action.doAction();
        Assertions.assertTrue(player.isShieldDeploy());
    }

    @Test
    public void shotActionTest(){
        Action shot = null;
        for(Direction direction : Direction.values()) {
            try {
                shot = new Shot(game, game.getCurrentPlayer(), direction);
                break;
            } catch (NoMoreBulletInWeaponException | NotValidDirectionException ignored) {}
        }
        Assertions.assertNotNull(shot);
        Player otherPlayer = game.getPlayers().get(1);
        int currentEnergyOtherPlayer = otherPlayer.getEnergy();
        int currentEnergyCurrentPlayer = game.getCurrentPlayer().getEnergy();
        shot.doAction();
        Assertions.assertEquals(currentEnergyCurrentPlayer - game.getCurrentPlayer().getClassPlayer().getShootCost(), game.getCurrentPlayer().getEnergy());
        Assertions.assertEquals(currentEnergyOtherPlayer - otherPlayer.getClassPlayer().getPenaltyShoot(), otherPlayer.getEnergy());
    }

    @Test
    public void dropBombActionTest() {
        Player player = game.getCurrentPlayer();
        Action action = null;
        Direction savedDirection = null;
        for(Direction direction : Direction.values()) {
            try {
                action = new DropBomb(game, game.getCurrentPlayer(), direction);
                savedDirection = direction;
                break;
            } catch (NotValidDirectionException ignored) {}
        }
        Assertions.assertNotNull(action);
        action.doAction();
        Point bombPosition = new Point(player.getPoint().getA() + savedDirection.getDeltaX(), player.getPoint().getB() + savedDirection.getDeltaY());
        Assertions.assertTrue(game.getGrid().getBoard().get(bombPosition).getB() instanceof Bomb);
    }

}
