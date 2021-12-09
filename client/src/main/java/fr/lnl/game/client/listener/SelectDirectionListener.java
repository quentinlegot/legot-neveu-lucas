package fr.lnl.game.client.listener;

import fr.lnl.game.client.view.Window;
import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.action.Action;
import fr.lnl.game.server.listener.AbstractModelListening;
import fr.lnl.game.server.listener.ModelListener;

public class SelectDirectionListener extends AbstractModelListening {

    private final Game game;
    private final Action action;
    private final Window window;

    public SelectDirectionListener(Game game, Window window, Action action) {
        this.game = game;
        this.window = window;
        this.action = action;
    }

    @Override
    public void updateModel(Object obj) {
        game.setSelectedAction(action);
        window.setSelectedReunionAction(null);
        window.getNextPlayerButtonListener().updateModel(obj);

    }
}
