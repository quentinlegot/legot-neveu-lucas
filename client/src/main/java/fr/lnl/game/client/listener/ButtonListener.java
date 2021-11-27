package fr.lnl.game.client.listener;

import fr.lnl.game.client.App;

public class ButtonListener extends AbstractModelListening{


    @Override
    public void addListener(ModelListener e) {
       this.listeners.add(e);
    }

    @Override
    public void removalListener(ModelListener e) {
        this.listeners.remove(e);
    }


    @Override
    public void updateModel(Object event) {
        App.game.nextCurrentPlayer();
        App.updateView();
    }
}
