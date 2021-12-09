package fr.lnl.game.server.games.grid.elements;

import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Point;

/**
 * Super class of {@link Bomb} and {@link Mine}
 */
public abstract class Explosive extends AbstractBox implements InteractiveBox {

    /**
     * Position of the explosive
     */
    protected final Point point;
    /**
     * Owner of the explosive
     */
    protected final Player player;

    public Explosive(Player player, Point point){
        this.player = player;
        this.point = point;
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
        explode(grid);
    }

    /**
     * Apply damage to players and delete this object
     * @param grid game's grid
     */
    protected void explode(Grid grid) {
        grid.getBoard().get(point).setB(null);
    }

    public Player getPlayer() {
        return player;
    }
}
