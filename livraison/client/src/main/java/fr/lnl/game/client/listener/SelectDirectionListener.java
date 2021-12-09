package fr.lnl.game.client.listener;

import fr.lnl.game.client.view.Window;
import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.action.Action;
import fr.lnl.game.server.listener.AbstractModelListening;

/**
 * Call when the player selected the direction where it wants to play it
 */
public class SelectDirectionListener extends AbstractModelListening {

    private final Game game;
    private final Action action;
    private final Window window;

    public SelectDirectionListener(Game game, Window window, Action action) {
        this.game = game;
        this.window = window;
        this.action = action;
    }

    /**
     * This method is call when the player click on the button to select the direction of the previously selected action
     * @param obj contain information about the event like the button where the player clicked
     */
    @Override
    public void updateModel(Object obj) {
        game.setSelectedAction(action);
        window.setSelectedReunionAction(null);
        window.getNextPlayerButtonListener().updateModel(obj);

    }
}
