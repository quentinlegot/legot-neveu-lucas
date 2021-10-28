package fr.lnl.game.server.games.action;


import fr.lnl.game.server.utils.Point;

import java.util.List;

public interface Action {
    void doAction();
    boolean isPossible();
    List<Point> getValidPoint();

}
