package fr.lnl.game.server.games.grid.elements;

import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Point;

public abstract class Explosive extends AbstractBox implements InteractiveBox {

    @Override
    public void interact(Grid grid, Player player, Point position) {
        grid.getBoard().get(position).setB(null);
    }
}
