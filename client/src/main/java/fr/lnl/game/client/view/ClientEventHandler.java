package fr.lnl.game.client.view;

import fr.lnl.game.client.App;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ClientEventHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        App.game.nextCurrentPlayer();
        App.playerList.get(App.game.getCurrentPlayer()).getView().show();
    }
}
