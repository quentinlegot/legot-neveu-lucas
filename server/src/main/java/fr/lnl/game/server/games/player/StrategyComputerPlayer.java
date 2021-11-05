package fr.lnl.game.server.games.player;

import fr.lnl.game.server.games.action.Action;
import fr.lnl.game.server.utils.Point;

public class StrategyComputerPlayer extends AbstractPlayer{

    public StrategyComputerPlayer(Integer id, Point point, ClassPlayer classPlayer) {
        super(id,point,false, classPlayer);
    }

    @Override
    public Action choseAction() {
        return null;
    }
}
