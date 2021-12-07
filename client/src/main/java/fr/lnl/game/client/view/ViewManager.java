package fr.lnl.game.client.view;

import fr.lnl.game.client.ClientPlayer;
import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.player.Player;

import java.util.HashMap;

public class ViewManager {


    private final HashMap<Player, ClientPlayer> players;
    private final Game game;

    public ViewManager(HashMap<Player, ClientPlayer> players, Game game, Class<? extends View> viewType) {
        this.players = players;
        this.game = game;
        if(viewType == Terminal.class) {
            terminalView();
        } else {
            updateView();
        }
    }

    public void updateView() {
        players.get(game.getCurrentPlayer()).getView().show();
    }

    public void terminalView() {
        while(true) {
            Player player = game.getCurrentPlayer();
            players.get(game.getCurrentPlayer()).getView().show();
            game.play();
            System.out.println("Le joueur ordinateur numéro " + player.getId() + " a joué");
            System.out.println("Il a joué l'action: " + game.getSelectedAction());
        }
    }

    public void displayWinner(Player winner) {
        players.get(game.getCurrentPlayer()).getView().displayWinner(winner);
    }
}
