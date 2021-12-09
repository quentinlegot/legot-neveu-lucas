package fr.lnl.game.server.games.player;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.action.Action;
import fr.lnl.game.server.games.action.Nothing;
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
    public Action choseAction(Game game){
        Action action;
        switch (getActions().size()){
            case 0 -> action = new Nothing();
            case 1 -> action = getActions().get(0);
            default -> {
                return strategy(game);
            }
        }
        return action;
    }

    /**
     *
     * Method belonging to classes which extend this class to define the specific strategy of the player
     * @return the chosen action between all available
     */
    public abstract Action strategy(Game game);
}
