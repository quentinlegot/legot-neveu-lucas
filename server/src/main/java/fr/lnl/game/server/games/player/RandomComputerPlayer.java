package fr.lnl.game.server.games.player;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.action.Action;
import fr.lnl.game.server.games.action.Nothing;
import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.utils.Point;

import java.util.Random;

public class RandomComputerPlayer extends ComputerPlayer {

    public RandomComputerPlayer(Integer id, Point point, ClassPlayer classPlayer) {
        super(id,point, classPlayer);
    }

    /**
     * Choose an action fully randomly
     * @return an action between all available
     */
    @Override
    public Action strategy(Game game) {
        Action action = null;
        Random random = new Random();
        while (action == null || !action.isPossible()) {
            action = getActions().get(random.nextInt(0, getActions().size()));
        }
        return action;
    }

    @Override
    public String toString() {
        return "Random";
    }
}
