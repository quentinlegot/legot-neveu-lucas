package fr.lnl.game.server.games.grid.elements;

public interface Box {

    /**
     * @return true if this box is locked (can't place wall on this position)
     * @see fr.lnl.game.server.games.grid.build.LockGridFactoryBuilder
     */
    boolean isLock();

    void setLock(boolean lock);
}
