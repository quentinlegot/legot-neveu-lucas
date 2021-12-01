package fr.lnl.game.client.view;

import fr.lnl.game.client.ClientPlayer;
import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.player.Player;
import javafx.util.Duration;

import java.util.HashMap;

public class ViewManager {


    private final HashMap<Player, ClientPlayer> players;
    private final Game game;
    private Boolean needUpdate = true;

    public ViewManager(HashMap<Player, ClientPlayer> players, Game game) {
        this.players = players;
        this.game = game;
        TimerService timer = new TimerService();
        timer.setPeriod(Duration.millis(30));
        updateView();
        timer.start();
    }

    public void updateView() {
        players.get(game.getCurrentPlayer()).getView().show();
        setNeedUpdate(false);
    }

    public Boolean getNeedUpdate() {
        return needUpdate;
    }

    public void setNeedUpdate(Boolean needUpdate) {
        this.needUpdate = needUpdate;
    }
}
