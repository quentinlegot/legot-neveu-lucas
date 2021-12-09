package fr.lnl.game.server.games.action;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.grid.elements.Mine;
import fr.lnl.game.server.games.player.Player;

/**
 * Used when player want to drop a {@link Mine}, Mine only explode when someone walks on it
 */
public class DropMine extends DropObject {

    public DropMine(Game game, Player player, Direction8Axis direction) throws NotValidDirectionException {
        super(game, player, direction);

    }

    /**
     * Drop a mine in player's selected direction and decrement its energy
     */
    @Override
    public void doAction() {
        game.getGrid().getBoard().get(point).setB(new Mine(player, point));
        game.getCurrentPlayer().decrementEnergy(player.getClassPlayer().getMineCost());
    }

}
