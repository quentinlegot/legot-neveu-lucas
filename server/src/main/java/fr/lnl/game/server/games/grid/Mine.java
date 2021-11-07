package fr.lnl.game.server.games.grid;

import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Point;

public class Mine extends Explosive{

    @Override
    public void interact(Grid grid, Player player, Point position) {
        player.decrementEnergy(player.getClassPlayer().getPenaltyMine());
        super.interact(grid, player, position);
    }
}
