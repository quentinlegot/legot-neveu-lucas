package fr.lnl.game.server.mock;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.grid.build.GridFactoryBuilder;
import fr.lnl.game.server.games.player.Player;

import java.util.List;

public class Mock {

    public GridFactoryBuilder buildStrategy;
    public Game game;
    public Grid grid;

    public Mock(List<Player> players) {
        this.buildStrategy = MockGridFactoryBuilder.create().gridDimensions(16, 16).playersList(players).wallProbability(0.80F).energyProbability(0.95F);
        game = new Game(buildStrategy, players);
        this.grid = game.getGrid();
    }

}
