package fr.lnl.game.server.games.action;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.grid.Mine;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Point;

import java.util.List;
import java.util.Random;

public class DropMine extends DropObject {
    public DropMine(Game game){
        super(game);
    }
    @Override
    public void doAction() {
        List<Point> points = getValidPoint();
        Random random = new Random();
        Point point = points.get(random.nextInt(0,points.size()-1));
        Mine mine = new Mine();
        getGame().getGrid().getBoard().get(point).setB(mine);
        getGame().getCurrentPlayer().decrementEnergy(getGame().getCurrentPlayer().getClassPlayer().getMineCost());
    }

    @Override
    public boolean isPossible() {
        return super.isPossible();
    }

    @Override
    public List<Point> getValidPoint() {
        return super.getValidPoint();
    }

}
