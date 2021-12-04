package fr.lnl.game.server.games.grid.elements;

import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Point;

public class EnergyBall extends AbstractBox implements InteractiveBox{

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();// no var to test
    }

    @Override
    public void interact(Grid grid, Player player, Point position) {
        player.incrementEnergy(player.getClassPlayer().getGainEnergy());
        grid.getBoard().get(position).setB(null);
    }
}
