package fr.lnl.game.client;

import fr.lnl.game.client.view.View;
import fr.lnl.game.server.games.player.Player;

public class ClientPlayer {

    private final Player serverPlayer;
    private final View view;

    public ClientPlayer(Player serverPlayer, View view) {
        this.serverPlayer = serverPlayer;
        this.view = view;
    }

    public Player getServerPlayer() {
        return serverPlayer;
    }

    public View getView() {
        return view;
    }
}
