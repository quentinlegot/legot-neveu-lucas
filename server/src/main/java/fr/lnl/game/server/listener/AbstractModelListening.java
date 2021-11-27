package fr.lnl.game.server.listener;
import java.util.List;

public abstract class AbstractModelListening implements ModelListener {

    protected List<ModelListener> listeners;

    public void addListener(ModelListener e) {
        this.listeners.add(e);
    }

    public void removalListener(ModelListener e) {
        this.listeners.remove(e);
    }

    public void fireChange(){
        for(ModelListener e : listeners){
            e.updateModel(this);
        }
    }

}
