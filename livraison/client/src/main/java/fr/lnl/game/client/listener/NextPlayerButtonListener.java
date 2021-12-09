package fr.lnl.game.client.listener;

import fr.lnl.game.client.App;
import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.listener.AbstractModelListening;

/**
 * This method is call when the current player have selected an action to play (or if it's a computer player, will
 * select it), then we call {@link Game#play()} and we update the view
 */
public class NextPlayerButtonListener extends AbstractModelListening {

    private final Game game;
    private final DisplayWinnerEvent displayWinnerEvent;

    public NextPlayerButtonListener(Game game) {
        this.game = game;
        this.displayWinnerEvent = new DisplayWinnerEvent();
    }

    /**
     * Call when clicking on "SUIVANT" button if current player is a computer player or after the human player selected
     * action it want to play
     */
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
