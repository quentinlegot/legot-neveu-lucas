package fr.lnl.game.server.listener;

public class GameFinishEvent extends AbstractModelListening {

    private final Object lock = new Object();

    @Override
    public void updateModel(Object obj) {
        synchronized (lock) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);
    }
}
