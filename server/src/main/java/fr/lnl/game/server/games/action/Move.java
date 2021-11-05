package fr.lnl.game.server.games.action;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.grid.*;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Pair;
import fr.lnl.game.server.utils.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Move extends AbstractAction {

    private final Point point;

    public Move(Game game, Player player, Direction direction) throws NotValidDirectionException {
        super(game, player);
        HashSet<Point> points = new HashSet<>(getValidPoint());
        Point playerPosition = player.getPoint();
        Point newPosition = new Point(playerPosition.getA() + direction.deltaX, playerPosition.getB() + direction.deltaY);
        if(!points.contains(newPosition)) {
            throw new NotValidDirectionException(direction + " isn't a valid position");
        }
        this.point = newPosition;
    }

    @Override
    public void doAction() {
        Player player = getGame().getCurrentPlayer();
        getGame().getGrid().getBoard().get(player.getPoint()).setA(null);
        getGame().getGrid().getBoard().get(this.point).setA(player);
        player.setPoint(this.point);
        player.decrementEnergy(player.getClassPlayer().getMoveCost());
        Box box = getGame().getGrid().getBoard().get(this.point).getB();
        if (box instanceof Mine){
            player.decrementEnergy(player.getClassPlayer().getPenaltyMine());
            getGame().getGrid().getBoard().get(this.point).setB(null);
        }
        if(box instanceof Bomb){
            player.decrementEnergy(player.getClassPlayer().getPenaltyBomb());
            getGame().getGrid().getBoard().get(this.point).setB(null);
        }
        if(box instanceof EnergyBall){
            player.incrementEnergy(player.getClassPlayer().getGainEnergy());
            getGame().getGrid().getBoard().get(this.point).setB(null);
        }
    }

    @Override
    public boolean isPossible() {
        return !getValidPoint().isEmpty();
    }

    @Override
    public List<Point> getValidPoint() {
        List<Point> listMoves = new ArrayList<>();
        HashMap<Point, Pair<Player, Box>> board = getGame().getGrid().getBoard();
        Point position = getPlayer().getPoint();
        for (int deltarow = -1; deltarow <= 1; deltarow++) {
            for (int deltacolumn = -1; deltacolumn <= 1; deltacolumn++) {
                if(deltarow == 0 || deltacolumn == 0){
                    if(getGame().getGrid().boardPositionIsValid(position.getA(),deltarow,position.getB(),deltacolumn)){
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

    public enum Direction {

        UP(-1, 0),
        DOWN(1, 0),
        LEFT(0, -1),
        RIGHT(-1, 0);

        private final int deltaX;
        private final int deltaY;

        Direction(int i, int i1) {
            this.deltaX = i;
            this.deltaY = i1;
        }

        public int getDeltaX() {
            return deltaX;
        }

        public int getDeltaY() {
            return deltaY;
        }
    }
}
