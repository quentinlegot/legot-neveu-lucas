package fr.lnl.game.server.games.action;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.grid.elements.Box;
import fr.lnl.game.server.games.grid.elements.InteractiveBox;
import fr.lnl.game.server.games.grid.elements.Wall;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Pair;
import fr.lnl.game.server.utils.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Used when the player want to move in a direction, it can move in a direction when there is another player or a wall
 */
public class Move extends AbstractAction {

    private final Point point;
    private final Direction4Axis direction;

    public Move(Game game, Player player, Direction4Axis direction) throws NotValidDirectionException {
        super(game, player);
        List<Point> points = getValidPoint();
        Point playerPosition = player.getPosition();
        Point newPosition = new Point(playerPosition.getA() + direction.getDeltaX(), playerPosition.getB() + direction.getDeltaY());
        if(!points.contains(newPosition)) {
            throw new NotValidDirectionException(direction + " isn't a valid position");
        }
        this.point = newPosition;
        this.direction = direction;
    }

    /**
     * Move player to its new position and decrement its point
     */
    @Override
    public void doAction() {
        game.getGrid().getBoard().get(player.getPosition()).setA(null);
        game.getGrid().getBoard().get(this.point).setA(player);
        player.setPosition(this.point);
        player.decrementEnergy(player.getClassPlayer().getMoveCost());
        Box box = game.getGrid().getBoard().get(this.point).getB();
        if(box instanceof InteractiveBox interactiveBox) {
            interactiveBox.interact(game.getGrid(), player, this.point);
        }
    }

    /**
     * @return true if player can play this action in current context, false otherwise
     */
    @Override
    public boolean isPossible() {
        return !getValidPoint().isEmpty();
    }

    /**
     * @return a list of point where it's possible to move.
     * We add a point to the list where there is nothing on the board.
     * @see Action#getValidPoint()
     */

    @Override
    public List<Point> getValidPoint() {
        List<Point> listMoves = new ArrayList<>();
        HashMap<Point, Pair<Player, Box>> board = game.getGrid().getBoard();
        Point position = player.getPosition();
        for (int deltarow = -1; deltarow <= 1; deltarow++) {
            for (int deltacolumn = -1; deltacolumn <= 1; deltacolumn++) {
                if(deltarow == 0 || deltacolumn == 0){
                    if(game.getGrid().boardPositionIsValid(position.getA(),deltarow,position.getB(),deltacolumn)){
                        Point neighbour = new Point(position.getA() + deltarow, position.getB() + deltacolumn);
                        Pair<Player, Box> state = board.get(neighbour);
                        if(state.getA() == null && !(state.getB() instanceof Wall)){
                            listMoves.add(neighbour);
                        }
                    }
                }
            }
        }
        return listMoves;
    }

    @Override
    public Point getPoint() {
        return point;
    }

    public Direction4Axis getDirection() {
        return direction;
    }


}
