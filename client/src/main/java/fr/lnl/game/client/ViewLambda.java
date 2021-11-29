package fr.lnl.game.client;

import fr.lnl.game.client.view.View;
import fr.lnl.game.server.games.player.Player;

@FunctionalInterface
public interface ViewLambda {

    View createViewLambda(Player player);

}
