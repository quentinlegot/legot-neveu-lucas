package fr.lnl.game.client.listener;

import fr.lnl.game.server.listener.ModelListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public record ClientEventHandler(
        ModelListener listener) implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        listener.updateModel(event);
    }
}
