package fr.lnl.game.server.games.grid.elements;

import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Point;

public abstract class Explosive extends AbstractBox implements InteractiveBox {

    Player player;

    public Explosive(Player player){
        this.player = player;
    }

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
