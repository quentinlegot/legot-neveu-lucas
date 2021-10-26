package fr.lnl.game.client.view;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.player.Player;

public class Terminal extends AbstractView {

    public Terminal(Game game, Player player) {
        super(game, player);
    }

    public void show() {
        // TODO: 26/10/2021
        game.getGrid().printGrid();
    }

}
