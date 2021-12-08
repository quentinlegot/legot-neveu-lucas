package fr.lnl.game.server.games.grid.elements;

import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Point;

/**
 * Super class of {@link Bomb} and {@link Mine}
 */
public abstract class Explosive extends AbstractBox implements InteractiveBox {

    Player player;

    public Explosive(Player player){
        this.player = player;
    }

    /**
     * Destroy this element on explosion
     * @param grid Game's grid
     * @param player the player who walks on this element
     * @param position position of this element on the grid
     * @see InteractiveBox#interact(Grid, Player, Point)
     */
    @Override
    public void interact(Grid grid, Player player, Point position) {
        if(grid.getBoard().get(position).getB() == this){
            grid.getBoard().get(position).setB(null);
        }
    }

    public Player getPlayer() {
        return player;
    }
}
