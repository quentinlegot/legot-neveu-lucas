package fr.lnl.game.client.view;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.player.Player;

public abstract class AbstractView implements View {

    protected final Player player;
    protected Game game;

    public AbstractView(Game game, Player player) {
        this.game = game;
        this.player = player;
    }
}
