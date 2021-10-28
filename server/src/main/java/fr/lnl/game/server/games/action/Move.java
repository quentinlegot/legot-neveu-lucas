package fr.lnl.game.server.games.action;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.grid.*;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Pair;
import fr.lnl.game.server.utils.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Move extends AbstractAction {
    public Move(Game game) {
        super(game);
    }

    @Override
    public void doAction() {
        List<Point> points = getValidPoint();
        Point nextPositon = choseRandomPoint(points);
        Player player = getGame().getCurrentPlayer();
        getGame().getGrid().getBoard().get(player.getPoint()).setA(null);
        getGame().getGrid().getBoard().get(nextPositon).setA(player);
        player.setPoint(nextPositon);
        player.decrementEnergy(player.getClassPlayer().getMoveCost());
        Box box = getGame().getGrid().getBoard().get(nextPositon).getB();
        if (box instanceof Mine){
            player.decrementEnergy(player.getClassPlayer().getPenaltyMine());
            getGame().getGrid().getBoard().get(nextPositon).setB(null);
        }
        if(box instanceof Bomb){
            player.decrementEnergy(player.getClassPlayer().getPenaltyBomb());
            getGame().getGrid().getBoard().get(nextPositon).setB(null);
        }
        if(box instanceof EnergyBall){
            player.incrementEnergy(player.getClassPlayer().getGainEnergy());
            getGame().getGrid().getBoard().get(nextPositon).setB(null);
        }
    }

    @Override
    public boolean isPossible() {
        return !getValidPoint().isEmpty();
    }

    public List<Point> getValidPoint() {
        List<Point> listMoves = new ArrayList<>();
        HashMap<Point, Pair<Player, Box>> board = getGame().getGrid().getBoard();
        Point position = getGame().getCurrentPlayer().getPoint();
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
}
