package fr.lnl.game.server.games.action;


import fr.lnl.game.server.utils.Point;

import java.util.List;

public interface Action {

    /**
     * Call by {@link fr.lnl.game.server.games.Game} when player do this action
     */
    void doAction();

    /**
     *
     * @return true if this action is possible, false otherwise
     */
    boolean isPossible();

    /**
     * Used by {@link Move}, {@link Shot} and {@link DropObject} to list all direction when the action is possible
     * @return a list a point where the action is possible (not block by a wall per example)
     */
    List<Point> getValidPoint();

}
