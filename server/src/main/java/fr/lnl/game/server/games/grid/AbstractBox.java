package fr.lnl.game.server.games.grid;

public class AbstractBox implements Box{

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

