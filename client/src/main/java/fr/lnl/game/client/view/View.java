package fr.lnl.game.client.view;

import fr.lnl.game.server.games.player.Player;

/**
 * View interface, implemented by Terminal and Window.
 */
public interface View {

    /**
     * used to update screen
     */
    void show();

    /**
     * Used to display the name of the winner
     * @param winner the player who win the game, can be Null
     */
    void displayWinner(/* Nullable */ Player winner);
}
