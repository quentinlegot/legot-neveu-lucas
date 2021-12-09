package fr.lnl.game.server.games.action;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.grid.elements.Box;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Pair;
import fr.lnl.game.server.utils.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Super class used by {@link DropMine} and {@link DropBomb}
 */
public abstract class DropObject extends AbstractAction {

    protected final Point point;
    private final Direction8Axis direction;

    /**
     * @param player basically current player
     * @param direction chosen direction
     * @throws NotValidDirectionException throw when the chosen direction is invalid
     */
    public DropObject(Game game, Player player, Direction8Axis direction) throws NotValidDirectionException {
        super(game, player);
        List<Point> points = getValidPoint();
        Point playerPosition = player.getPosition();
        Point dropDirection = new Point(playerPosition.getA() + direction.getDeltaX(), playerPosition.getB() + direction.getDeltaY());
        if(!points.contains(dropDirection)) {
            throw new NotValidDirectionException(direction + " isn't a valid position");
        }
        this.point = dropDirection;
        this.direction = direction;
    }

    /**
     *
     * @return true if player can play this action in current context, false otherwise
     */
    @Override
    public boolean isPossible() {
        return !getValidPoint().isEmpty();
    }

    /**
     * @return a list of point where it's possible to place a bomb of a mine.
     * We add a point where there is nothing on the board.
     * @see Action#getValidPoint()
     */
    public List<Point> getValidPoint() {
        List<Point> listMoves = new ArrayList<>();
        HashMap<Point, Pair<Player, Box>> board = game.getGrid().getBoard();
        Point position = player.getPosition();
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

    public Direction8Axis getDirection() {
        return direction;
    }

    @Override
    public Point getPoint() {
        return point;
    }
}
