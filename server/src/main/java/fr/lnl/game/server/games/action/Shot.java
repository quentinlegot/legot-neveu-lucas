package fr.lnl.game.server.games.action;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.grid.Wall;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.games.weapon.Weapon;
import fr.lnl.game.server.utils.Point;

import java.util.ArrayList;
import java.util.List;

public class Shot extends AbstractAction {

    public Shot(Game game, Player player) {
        super(game, player);
    }

    /**
     * @deprecated a rewrite -> L'aléatoire ne devrait pas être ici, mais au moment de l'instanciation comme dans {@link Move}
     */
    @Deprecated
    @Override
    public void doAction() {
        player.decrementEnergy(player.getClassPlayer().getShootCost());
        game.getGrid().getBoard().get(choseRandomPoint(getValidPoint())).getA()
        .decrementEnergy(player.getClassPlayer().getPenaltyShoot());
    }

    @Override
    public boolean isPossible() {
        return !getValidPoint().isEmpty();
    }

    /**
     * @deprecated voir {@link Shot#doAction()}, surement renommé en isValidPoint(Point): bool après rework
     */
    @Deprecated(forRemoval = true, since = "07/11/2021")
    @Override
    public List<Point> getValidPoint() {
        List<Point> listMoves = new ArrayList<>();
        Point position = game.getCurrentPlayer().getPoint();
        Weapon weapon = game.getCurrentPlayer().getWeapon();
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

    @Deprecated(since = "07/11/2021", forRemoval = true)
    public Point seeNeibourg(Point point, int delta, int range, boolean isVertical) {
        Point neibourg = null;
        if (isVertical) {
            if (game.getGrid().boardVerticalIsValid(point.getA(), delta)) {
                neibourg = new Point(point.getA() + delta, point.getB());
            }
        } else {
            if (game.getGrid().boardHorizontalIsValid(point.getB(), delta)) {
                neibourg = new Point(point.getA(), point.getB() + delta);
            }
        }
        if (game.getGrid().getBoard().get(neibourg).getB() instanceof Wall || range + delta < 0) {
            return null;
        }
        if(game.getGrid().getBoard().get(neibourg).getA() instanceof Player){
            return neibourg;
        }
        return seeNeibourg(neibourg,delta,range - 1,isVertical);
    }
}
