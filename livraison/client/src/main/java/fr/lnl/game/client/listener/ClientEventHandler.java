package fr.lnl.game.client.listener;

import fr.lnl.game.server.listener.ModelListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * implementation of a listener from JavaFX {@link EventHandler}
 */
public record ClientEventHandler(ModelListener listener) implements EventHandler<ActionEvent> {

    /**
     * This method is call by JavaFX when we click to the button
     * @param event event class created when clicking on the element
     */
    @Override
    public void handle(ActionEvent event) {
        listener.updateModel(event);
    }
}
