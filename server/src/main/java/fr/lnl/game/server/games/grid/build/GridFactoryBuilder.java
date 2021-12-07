package fr.lnl.game.server.games.grid.build;

import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.player.Player;

import java.util.List;

public interface GridFactoryBuilder {

    Grid build();
    void initPlacePlayers();
    GridFactoryBuilder energyProbability(float probability);
    GridFactoryBuilder wallProbability(float probability);
    GridFactoryBuilder gridDimensions(int row, int columns);
    GridFactoryBuilder playersList(List<Player> players);

}
