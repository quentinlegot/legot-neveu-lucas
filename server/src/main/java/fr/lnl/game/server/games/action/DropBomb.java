package fr.lnl.game.server.games.action;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.grid.Bomb;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Point;

import java.util.List;

public class DropBomb extends DropObject {

    public DropBomb(Game game, Player player){
        super(game, player);
    }

    /**
     * @deprecated même principe que {@link Shot#doAction()}
     */
    @Deprecated
    @Override
    public void doAction() {
        List<Point> points = getValidPoint();
        Point point = choseRandomPoint(points);
        game.getGrid().getBoard().get(point).setB(new Bomb());
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

