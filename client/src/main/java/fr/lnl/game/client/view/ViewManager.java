package fr.lnl.game.client.view;

import fr.lnl.game.client.ClientPlayer;
import fr.lnl.game.client.listener.DisplayWinnerEvent;
import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.player.HumanPlayer;
import fr.lnl.game.server.games.player.Player;

import java.util.HashMap;

public record ViewManager(
        HashMap<Player, ClientPlayer> players,
        Game game, Class<? extends View> viewType) {


    public void updateView() {
        players.get(game.getCurrentPlayer()).getView().show();
    }

    public void terminalView() {
        DisplayWinnerEvent displayWinnerEvent = new DisplayWinnerEvent();
        while (true) {
            Player player = game.getCurrentPlayer();
            players.get(game.getCurrentPlayer()).getView().show();
            if(game.getCurrentPlayer() instanceof HumanPlayer human) {
                game.setSelectedAction(players.get(human).getView().choseAction());
            }
            boolean isOver = game.play();
            System.out.println("Le joueur ordinateur numéro " + player.getId() + " a joué");
            System.out.println("Il a joué l'action: " + game.getSelectedAction());
            if (isOver) {
                displayWinnerEvent.updateModel(game.getWinner());
                System.exit(0);
            }
        }
    }

    public void displayWinner(Player winner) {
        players.get(game.getCurrentPlayer()).getView().displayWinner(winner);
    }

    public void run() {
        if (viewType == Terminal.class) {
            terminalView();
        } else {
            updateView();
        }
    }
}
