package fr.lnl.game.server.games.action;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.utils.Point;

import java.util.List;
import java.util.Random;

public abstract class AbstractAction<point> implements Action {
    private final Game game;

    public AbstractAction(Game game){
        this.game = game;
    }

    protected Game getGame() {
        return game;
    }

    protected Point choseRandomPoint(List<Point> getValidPoint) {
        Point point = null;
        switch (getValidPoint.size()) {
            case 0:
                break;
            case 1:
                point = getValidPoint.get(0);
            default: {
                Random random = new Random();
                point = getValidPoint.get(random.nextInt(0, getValidPoint.size()));
            }
        }
        return point;
    }
}
