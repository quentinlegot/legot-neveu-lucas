package fr.lnl.game.server.games.grid.elements;

import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Point;

/**
 * A box implemented by InteractiveBox is a box which do an action when someone walks on it
 */
public interface InteractiveBox {

    /**
     * Call when a player walk on it
     * @param grid Game's grid
     * @param player the player who walks on this element
     * @param position position of this element on the grid
     */
    void interact(Grid grid, Player player, Point position);

}
