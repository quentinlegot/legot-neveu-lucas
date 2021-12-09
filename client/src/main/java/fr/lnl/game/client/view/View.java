package fr.lnl.game.client.view;

import fr.lnl.game.server.games.action.Action;
import fr.lnl.game.server.games.player.Player;

public interface View {

    void show();

    void displayWinner(Player winner);
}
