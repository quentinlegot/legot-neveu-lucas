package fr.lnl.game.server.games.action;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.grid.Box;
import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.grid.Wall;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Pair;
import fr.lnl.game.server.utils.Point;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Move extends AbstractAction {
    public Move(Game game) {
        super(game);
    }

    @Override
    public void doAction() {

    }

    @Override
    public boolean isPossible() {
        return getValidPoint().isEmpty();
    }

    public List<Point> getValidPoint() {
        List<Point> listMoves = new LinkedList<>();
        HashMap<Point, Pair<Player, Box>> board = getGame().getGrid().getBoard();
        Point position = getGame().getCurrent_player().getPoint();
        for (int row = -1; row <= 1; row++) {
            for (int column = -1; column <= 1; column++) {
                if(Grid.caseisValid(position.getA(),row,position.getB(),column)){
                    Point neighbour = new Point(position.getA() + row, position.getB() + column);
                    Pair<Player, Box> state = board.get(neighbour);
                    if(state.getA() == null || state.getB() instanceof Wall){
                        listMoves.add(neighbour);
                    }
                }
            }
        }
        return listMoves;
    }

}
