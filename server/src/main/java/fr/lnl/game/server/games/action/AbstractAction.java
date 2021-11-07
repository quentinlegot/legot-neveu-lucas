package fr.lnl.game.server.games.action;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Point;

import java.util.List;
import java.util.Random;

public abstract class AbstractAction implements Action {

    protected final Game game;
    protected final Player player;

    public AbstractAction(Game game, Player player){
        this.game = game;
        this.player = player;
    }

    protected Point choseRandomPoint(List<Point> getValidPoint) {
        Point point = null;
        switch (getValidPoint.size()) {
            case 0:
                break;
            case 1:
                point = getValidPoint.get(0);
                break;
            default: {
                Random random = new Random();
                point = getValidPoint.get(random.nextInt(0, getValidPoint.size()));
            }
        }
        return point;
    }
}
