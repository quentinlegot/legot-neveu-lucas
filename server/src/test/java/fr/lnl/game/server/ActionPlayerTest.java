package fr.lnl.game.server;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.action.*;
import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.grid.elements.Bomb;
import fr.lnl.game.server.games.player.ClassPlayer;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.games.player.RandomComputerPlayer;
import fr.lnl.game.server.mock.Mock;
import fr.lnl.game.server.utils.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ActionPlayerTest {

    private Grid grid;
    private Game game;

    @BeforeEach
    public void mock() {
        List<Player> players = Arrays.asList(new RandomComputerPlayer(1,null, ClassPlayer.DEFAULT),
                new RandomComputerPlayer(2,null, ClassPlayer.DEFAULT));
        Mock mock = new Mock(players);
        this.grid = mock.grid;
        this.game = mock.game;
        Assertions.assertEquals(game.getPlayers().get(0), game.getCurrentPlayer());
    }

    @Test
    public void moveActionTest() {
        Action move = null;
        Point oldPoint = game.getCurrentPlayer().getPosition();
        Direction4Axis savedDirection = null;
        for(Direction4Axis direction : Direction4Axis.values()) {
            try {
                move = new Move(game, game.getCurrentPlayer(), direction);
                savedDirection = direction;
                break;
            } catch (NotValidDirectionException ignored) {}
        }
        Assertions.assertNotNull(move);
        move.doAction();
        Point newPoint = game.getCurrentPlayer().getPosition();
        Assertions.assertEquals(newPoint,
                new Point(oldPoint.getA() + savedDirection.getDeltaX(),
                        oldPoint.getB() + savedDirection.getDeltaY()
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
        for(Direction4Axis direction : Direction4Axis.values()) {
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
        Direction8Axis savedDirection = null;
        for(Direction8Axis direction : Direction8Axis.values()) {
            try {
                action = new DropBomb(game, game.getCurrentPlayer(), direction);
                savedDirection = direction;
                break;
            } catch (NotValidDirectionException ignored) {}
        }
        Assertions.assertNotNull(action);
        action.doAction();
        Point bombPosition = new Point(player.getPosition().getA() + savedDirection.getDeltaX(), player.getPosition().getB() + savedDirection.getDeltaY());
        Assertions.assertTrue(game.getGrid().getBoard().get(bombPosition).getB() instanceof Bomb);
    }

}
