package fr.lnl.game.client.listener;
import java.util.List;

public abstract class AbstractModelListening implements ModelListener {

    List<ModelListener> listeners;

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
