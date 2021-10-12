package fr.lnl.game.server.model;

public class ModelListening extends AbstractModelListening{


    @Override
    public void addListener(ModelListener e) {
       this.listeners.add(e);
    }

    @Override
    public void removalListener(ModelListener e) {
        this.listeners.remove(e);
    }


    @Override
    public void updateModel(Object obj) {

    }
}
