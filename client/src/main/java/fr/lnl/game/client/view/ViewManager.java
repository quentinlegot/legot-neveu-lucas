package fr.lnl.game.client.view;

import fr.lnl.game.client.ClientPlayer;
import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.player.Player;
import javafx.util.Duration;

import java.util.HashMap;

public class ViewManager {


    private final HashMap<Player, ClientPlayer> players;
    private final Game game;

    public ViewManager(HashMap<Player, ClientPlayer> players, Game game) {
        this.players = players;
        this.game = game;
        updateView();
    }

    public void updateView() {
        players.get(game.getCurrentPlayer()).getView().show();
    }
}
