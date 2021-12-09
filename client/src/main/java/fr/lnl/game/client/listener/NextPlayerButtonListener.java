package fr.lnl.game.client.listener;

import fr.lnl.game.client.App;
import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.listener.AbstractModelListening;
import fr.lnl.game.server.listener.ModelListener;
import javafx.scene.control.Alert;

public class NextPlayerButtonListener extends AbstractModelListening {


    private final Game game;
    private final DisplayWinnerEvent displayWinnerEvent;

    public NextPlayerButtonListener(Game game) {
        this.game = game;
        this.displayWinnerEvent = new DisplayWinnerEvent();
    }

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
        // Player player = game.getCurrentPlayer();
        boolean isOver = game.play();
        /* Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Un joueur ordinateur a joué");
        alert.setHeaderText("Le joueur ordinateur numéro" + player.getId() + " a joué");
        alert.setContentText("Il a joué l'action: " + game.getSelectedAction()); */
        App.getViewManager().updateView();
        if(isOver) {
            displayWinnerEvent.updateModel(game.getWinner());
            System.exit(0);
        }
        //alert.showAndWait();
    }
}
