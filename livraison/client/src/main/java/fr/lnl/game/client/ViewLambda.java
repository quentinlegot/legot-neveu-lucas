package fr.lnl.game.client;

import fr.lnl.game.client.view.View;
import fr.lnl.game.server.games.player.Player;

/**
 * Used as Lambda expression to instantiate a {@link View} per {@link Player} stored in {@link ClientPlayer}
 */
@FunctionalInterface
public interface ViewLambda {

    /**
     * A lambda create an anonymous class which implements this interface
     * @param player an instance of {@link Player} to store in {@link View}
     * @return an instance of view (depending on the first argument when launching the program)
     */
    View createViewLambda(Player player);

}
