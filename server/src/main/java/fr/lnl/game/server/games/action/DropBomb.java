package fr.lnl.game.server.games.action;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.grid.Bomb;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Point;

import java.util.List;

public class DropBomb extends DropObject {

    public DropBomb(Game game){
        super(game);
    }

    // voir pour la redondance de code au niveau de DropBomb, DropObject,DropMine
    @Override
    public void doAction() {
        List<Point> points = getValidPoint();
        Point point = choseRandomPoint(points);
        getGame().getGrid().getBoard().get(point).setB(new Bomb());
        Player player = getGame().getCurrentPlayer();
        player.decrementEnergy(player.getClassPlayer().getBombCost());
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

