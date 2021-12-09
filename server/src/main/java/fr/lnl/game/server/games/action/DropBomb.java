package fr.lnl.game.server.games.action;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.grid.elements.Bomb;
import fr.lnl.game.server.games.player.Player;

/**
 * Used when player want to drop a {@link Bomb}, bomb explode when someone walks on it and after 3 turns
 */
public class DropBomb extends DropObject {

    public DropBomb(Game game, Player player, Direction4Axis direction) throws NotValidDirectionException {
        super(game, player, direction);
    }

    /**
     * Drop a bomb in player's selected direction and decrement its energy
     */
    @Override
    public void doAction() {
        game.getGrid().getBoard().get(point).setB(new Bomb(point, game));
        player.decrementEnergy(player.getClassPlayer().getBombCost());
    }

}

