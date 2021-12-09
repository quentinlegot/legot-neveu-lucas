package fr.lnl.game.server.listener;

/**
 * Model implemented by {@link AbstractModelListening}
 */
public interface ModelListener {

    /**
     * this method is call everytime, an action on the view forces a controller to sens an update to model
     * @param obj can be used to send data to model
     */
    void updateModel(Object obj);
}
