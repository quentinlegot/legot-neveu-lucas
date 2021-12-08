package fr.lnl.game.server.games.player;

import fr.lnl.game.server.games.action.Action;
import fr.lnl.game.server.utils.Point;

/**
 * Super class of all Computer players
 */
public abstract class ComputerPlayer extends AbstractPlayer {

    public ComputerPlayer(Integer id, Point point, ClassPlayer classPlayer) {
        super(id, point, false, classPlayer);
    }

    /**
     * Call when an AI need to choose an action to execute
     * @return the chosen action
     */
    public abstract Action choseAction();
}
