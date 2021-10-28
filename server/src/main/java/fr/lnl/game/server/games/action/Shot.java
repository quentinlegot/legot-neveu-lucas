package fr.lnl.game.server.games.action;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.grid.Wall;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.games.weapon.Weapon;
import fr.lnl.game.server.utils.Point;

import java.util.ArrayList;
import java.util.List;

public class Shot extends AbstractAction {
    public Shot(Game game) {
        super(game);
    }

    @Override
    public void doAction() {
        List<Point> points = getValidPoint();
        Point point = choseRandomPoint(points);
        Player currentPlayer = getGame().getCurrentPlayer();
        currentPlayer.decrementEnergy(currentPlayer.getClassPlayer().getShootCost());
        Player targetPlayer = getGame().getGrid().getBoard().get(point).getA();
        targetPlayer.decrementEnergy(currentPlayer.getClassPlayer().getPenaltyShoot());
    }

    @Override
    public boolean isPossible() {
        return !getValidPoint().isEmpty();
    }

    @Override
    public List<Point> getValidPoint() {
        List<Point> listMoves = new ArrayList<>();
        Point position = getGame().getCurrentPlayer().getPoint();
        Weapon weapon = getGame().getCurrentPlayer().getWeapon();
        for (int delta = -1; delta <= 1; delta++) {
            if(delta != 0){
                Point verticalNeibourg = seeNeibourg(position,delta,weapon.getVerticalDistance(),true);
                if(verticalNeibourg != null){
                    listMoves.add(verticalNeibourg);
                }
                Point horizontalNeibourg = seeNeibourg(position,delta,weapon.getHorizontalDistance(),false);
                if(horizontalNeibourg != null){
                    listMoves.add(horizontalNeibourg);
                }
            }
        }
        return listMoves;
    }

    public Point seeNeibourg(Point point, int delta, int range, boolean isVertical) {
        Point neibourg = null;
        if (isVertical) {
            if (getGame().getGrid().boardVerticalIsValid(point.getA(), delta)) {
                neibourg = new Point(point.getA() + delta, point.getB());
            }
        } else {
            if (getGame().getGrid().boardHorizontalIsValid(point.getB(), delta)) {
                neibourg = new Point(point.getA(), point.getB() + delta);
            }
        }
        if (getGame().getGrid().getBoard().get(neibourg).getB() instanceof Wall || range + delta < 0) {
            return null;
        }
        if(getGame().getGrid().getBoard().get(neibourg).getA() instanceof Player){
            return neibourg;
        }
        return seeNeibourg(neibourg,delta,range - 1,isVertical);
    }
}
