package fr.lnl.game.server.games.grid;

import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Point;

public interface InteractiveBox {


    void interact(Grid grid, Player player, Point position);

}
