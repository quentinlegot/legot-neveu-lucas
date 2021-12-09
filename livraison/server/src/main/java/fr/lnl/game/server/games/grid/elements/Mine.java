package fr.lnl.game.server.games.grid.elements;

import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Point;

/**
 * A mine is an element which explose when someone walks on it, the explosion area is on 1 case only
 */
public class Mine extends Explosive{

    public Mine(Player player, Point point) {
        super(player, point);
    }

    @Override
    protected void explode(Grid grid) {
        Player player = grid.getBoard().get(point).getA();
        if(player != null)
            player.decrementEnergy(player.getClassPlayer().getPenaltyMine());
        super.explode(grid);
    }
}
