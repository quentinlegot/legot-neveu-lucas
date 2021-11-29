package fr.lnl.game.client.listener;

import fr.lnl.game.client.App;
import fr.lnl.game.server.listener.AbstractModelListening;

public class UpdateViewEvent extends AbstractModelListening {

    @Override
    public void updateModel(Object obj) {
        App.updateView();
    }
}
