package fr.lnl.game.server.games.action;

import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.player.Player;

public interface Action {
    void doAction();
    boolean isPossible();

}
