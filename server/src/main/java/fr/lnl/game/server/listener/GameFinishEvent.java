package fr.lnl.game.server.listener;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.utils.CrashException;

import java.util.concurrent.TimeUnit;

public class GameFinishEvent extends AbstractModelListening {

    private final Object lock = new Object();
    private final Game game;

    public GameFinishEvent(Game game) {
        this.game = game;
    }

    // TODO: 02/12/2021 méthode a revoir, notamment surement mettre un dialog sur la view Window et fermer dès que l'utilisateur a cliqué sur ok
    @Override
    public void updateModel(Object obj) {
        game.getDisplayWinnerEvent().updateModel(null);
        System.exit(0);
    }
}
