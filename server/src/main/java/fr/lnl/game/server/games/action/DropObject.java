package fr.lnl.game.server.games.action;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.grid.Box;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Pair;
import fr.lnl.game.server.utils.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class DropObject extends AbstractAction {

    public DropObject(Game game, Player player){
        super(game, player);
    }

    @Override
    public boolean isPossible() {
        return !getValidPoint().isEmpty();
    }

    public List<Point> getValidPoint() {
        List<Point> listMoves = new ArrayList<>();
        HashMap<Point, Pair<Player, Box>> board = game.getGrid().getBoard();
        Point position = player.getPoint();
        for (int row = -1; row <= 1; row++) {
            for (int column = -1; column <= 1; column++) {
                if(game.getGrid().boardPositionIsValid(position.getA(),row,position.getB(),column)){
                    Point neighbour = new Point(position.getA() + row, position.getB() + column);
                    Pair<Player, Box> state = board.get(neighbour);
                    if(state.getA() == null && state.getB() == null){
                        listMoves.add(neighbour);
                    }
                }
            }
        }
        return listMoves;
    }


}
