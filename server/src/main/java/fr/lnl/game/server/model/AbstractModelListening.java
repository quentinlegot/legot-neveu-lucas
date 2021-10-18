package fr.lnl.game.server.model;
import java.util.List;

public abstract class AbstractModelListening implements ModelListener {

    List<ModelListener> listeners;

    public abstract void addListener(ModelListener e);

    public abstract void removalListener(ModelListener e);

    public void fireChange(){
        for(ModelListener e : listeners){
            e.updateModel(this);
        }
    }

}
