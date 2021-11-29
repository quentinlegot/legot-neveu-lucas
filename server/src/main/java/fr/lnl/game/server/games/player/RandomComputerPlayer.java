package fr.lnl.game.server.games.player;

import fr.lnl.game.server.games.action.Action;
import fr.lnl.game.server.games.action.Nothing;
import fr.lnl.game.server.utils.Point;

import java.util.Random;

public class RandomComputerPlayer extends ComputerPlayer {

    public RandomComputerPlayer(Integer id, Point point, ClassPlayer classPlayer) {
        super(id,point, classPlayer);
    }

    @Override
    public Action choseAction() {
        Action action = null;
        switch (getActions().size()){
            case 0 -> action = new Nothing();
            case 1 -> action = getActions().get(0);
            default -> {
                Random random = new Random();
                while (action == null || !action.isPossible()) {
                    action = getActions().get(random.nextInt(0,getActions().size()));
                }
            }
        }
        return action;
    }
}
