package fr.lnl.game.client.view;

import fr.lnl.game.client.listener.ModelListener;
import fr.lnl.game.client.listener.ButtonListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ClientEventHandler implements EventHandler<ActionEvent> {

    private final ModelListener listener;

    public ClientEventHandler(ButtonListener listener) {
        this.listener = listener;
    }

    @Override
    public void handle(ActionEvent event) {
        listener.updateModel(event);
    }
}
