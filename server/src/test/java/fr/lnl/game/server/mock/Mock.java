package fr.lnl.game.server.mock;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.grid.build.BuildStrategy;
import fr.lnl.game.server.games.player.Player;

import java.util.List;

public class Mock {

    public BuildStrategy buildStrategy;
    public Game game;
    public Grid grid;

    public Mock(List<Player> players) {
        this.buildStrategy = new MockGridStrategy(new Grid(16,16, players),0.80F, 0.95F);
        game = new Game(buildStrategy, players, new MockDisplayWinner());
        this.grid = buildStrategy.getGrid();
    }

}
