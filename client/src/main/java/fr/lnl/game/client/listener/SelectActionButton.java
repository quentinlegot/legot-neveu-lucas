package fr.lnl.game.client.listener;

import fr.lnl.game.client.App;
import fr.lnl.game.client.view.Window;
import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.action.ReunionSameAction;
import fr.lnl.game.server.listener.AbstractModelListening;

public class SelectActionButton extends AbstractModelListening {

    private final Window window;
    private final ReunionSameAction reunionSameAction;
    private final Game game;

    public SelectActionButton(Game game, Window window, ReunionSameAction reunionSameAction) {
        this.game = game;
        this.window = window;
        this.reunionSameAction = reunionSameAction;
    }

    @Override
    public void updateModel(Object obj) {
        if(reunionSameAction.getActions().size() == 1){
            game.setSelectedAction(reunionSameAction.getActions().get(0));
            window.getNextPlayerButtonListener().updateModel(obj);
        } else {
            window.setSelectedReunionAction(reunionSameAction);
            App.getViewManager().updateView(); // update screen
        }



    }
}
