package fr.lnl.game.server.games.grid.elements;

import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Point;

/**
 * An EnergyBall is a box which give back a bit of player energy after it walks on the ball
 */
public class EnergyBall extends AbstractBox implements InteractiveBox{

    /**
     * Increment energy of player who walks on this element
     * @param grid Game's grid
     * @param player the player who walks on this element
     * @param position position of this element on the grid
     * @see InteractiveBox#interact(Grid, Player, Point)
     */
    @Override
    public void interact(Grid grid, Player player, Point position) {
        player.incrementEnergy(player.getClassPlayer().getGainEnergy());
        grid.getBoard().get(position).setB(null);
    }

    /**
     * Used by tests
     * @param obj the object to compare
     * @return true if obj is an instance of EnergyBall, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof EnergyBall;
    }
}
