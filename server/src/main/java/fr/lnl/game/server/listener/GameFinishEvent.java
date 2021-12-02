package fr.lnl.game.server.listener;

import fr.lnl.game.server.utils.CrashException;

import java.util.concurrent.TimeUnit;

public class GameFinishEvent extends AbstractModelListening {

    private final Object lock = new Object();

    // TODO: 02/12/2021 méthode a revoir, notamment surement mettre un dialog sur la view Window et fermer dès que l'utilisateur a cliqué sur ok
    @Override
    public void updateModel(Object obj) {
        synchronized (lock) {
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            } catch (InterruptedException e) {
                throw new CrashException(e.getMessage(), e);
            }
        }
        System.exit(0);
    }
}
