package fr.lnl.game.server.games.action;

import fr.lnl.game.server.utils.Point;

import java.util.List;

/**
 * This action is used when player doesn't want to move and/or loose energy
 */
public class Nothing extends AbstractAction {

    public Nothing() {
        super(null, null);
    }

    /**
     * doAction in this context don't execute any operation
     */
    @Override
    public void doAction(){
    }

    /**
     * This action is always possible
     * @return always true
     */
    @Override
    public boolean isPossible() {
        return true;
    }

    @Override
    public List<Point> getValidPoint() {
        return null;
    }
}
