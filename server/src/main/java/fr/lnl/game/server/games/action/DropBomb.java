package fr.lnl.game.server.games.action;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.grid.Bomb;
import fr.lnl.game.server.games.grid.Mine;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Point;

import java.util.List;
import java.util.Random;

public class DropBomb extends DropObject {

    public DropBomb(Game game){
        super(game);
    }

    //voir pour la redondance de code au niveau de DropBomb,DropObject,DropMine
    @Override
    public void doAction() {
        List<Point> points = getValidPoint();
        Random random = new Random();
        Point point = points.get(random.nextInt(0,points.size()-1));
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

