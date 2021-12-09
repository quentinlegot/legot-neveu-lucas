package fr.lnl.game.client;

import fr.lnl.game.client.view.View;
import fr.lnl.game.server.games.player.Player;

/**
 * Store a view per player
 */
public record ClientPlayer(Player serverPlayer, View view) {

    public Player getServerPlayer() {
        return serverPlayer;
    }

    public View getView() {
        return view;
    }
}
