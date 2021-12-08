package fr.lnl.game.server.games.grid.elements;

/**
 * AbstractBox is instantiable (not an abstract class), but when it's the case, instance doesn't represent anything,
 * it's only to see if there is a lock on this position or not
 */
public class AbstractBox implements Box {

    boolean lock;

    public AbstractBox(){
        lock = false;
    }

    @Override
    public void setLock(boolean lock) {
        this.lock = lock;
    }

    @Override
    public boolean isLock() {
        return lock;
    }
}

