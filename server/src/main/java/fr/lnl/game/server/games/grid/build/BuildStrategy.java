package fr.lnl.game.server.games.grid.build;

import fr.lnl.game.server.games.grid.Grid;

public interface BuildStrategy {

    void build();
    void initPlacePlayers();
    Grid getGrid();

}
