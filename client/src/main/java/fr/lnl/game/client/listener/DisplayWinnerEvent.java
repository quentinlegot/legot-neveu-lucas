package fr.lnl.game.client.listener;

import fr.lnl.game.client.App;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.listener.AbstractModelListening;

/**
 * Used to display the winner of the game
 */
public class DisplayWinnerEvent extends AbstractModelListening {

    /**
     * This method is call when the game is over
     * @param obj contains the winner of the game, can be null
     */
    @Override
    public void updateModel(Object obj) {
        Player winner = (Player) obj;
        App.getViewManager().displayWinner(winner);
    }
}
