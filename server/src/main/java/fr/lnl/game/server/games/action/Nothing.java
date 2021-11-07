package fr.lnl.game.server.games.action;

import fr.lnl.game.server.utils.Point;

import java.util.List;

public class Nothing extends AbstractAction {

    public Nothing() {
        super(null, null);
    }

    @Override
    public void doAction(){
    }

    @Override
    public boolean isPossible() {
        return true;
    }

    @Override
    public List<Point> getValidPoint() {
        return null;
    }
}
