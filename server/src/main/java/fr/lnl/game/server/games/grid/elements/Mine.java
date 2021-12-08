package fr.lnl.game.server.games.grid.elements;

import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Point;

/**
 * A mine is an element which explose when someone walks on it, the explosion area is on 1 case only
 */
public class Mine extends Explosive{

    public Mine(Player player) {
        super(player);
    }

    /**
     * Decrement energy of the player who walks on this element
     * @param grid Game's grid
     * @param player the player who walks on this element
     * @param position position of this element on the grid
     * @see InteractiveBox#interact(Grid, Player, Point)
     * @see Explosive#interact(Grid, Player, Point)
     */
    @Override
    public void interact(Grid grid, Player player, Point position) {
        player.decrementEnergy(player.getClassPlayer().getPenaltyMine());
        super.interact(grid, player, position);
    }
}
