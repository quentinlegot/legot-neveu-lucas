package fr.lnl.game.server.games.action;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.grid.elements.Wall;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.games.weapon.Weapon;
import fr.lnl.game.server.utils.Point;

import java.util.ArrayList;
import java.util.List;

public class Shot extends AbstractAction {

    private final Point point;
    private final Direction8Axis direction;

    public Shot(Game game, Player player, Direction8Axis direction) throws NoMoreBulletInWeaponException, NotValidDirectionException {
        super(game, player);
        if(player.getWeapon().getBullet() == 0) {
            throw new NoMoreBulletInWeaponException();
        }
        List<Point> points = getValidPoint();
        Point playerPosition = player.getPosition();
        Point shotDirection = new Point(playerPosition.getA() + direction.getDeltaX(), playerPosition.getB() + direction.getDeltaY());
        if(!points.contains(shotDirection)) {
            throw new NotValidDirectionException(direction + " isn't a valid position");
        }
        this.point = shotDirection;
        this.direction = direction;
    }

    public Shot(Game game, Player player) {
        super(game, player);
        point = null;
        direction = null;
    }

    /**
     * We decrement player's energy and shot on every of its opponents on the chosen direction by decrementing its
     * energy too
     */
    @Override
    public void doAction() {
        player.decrementEnergy(player.getClassPlayer().getShootCost());
        int range = direction.isVertical() ? player.getWeapon().getVerticalDistance() : player.getWeapon().getHorizontalDistance();
        for(int i=0; i < range; i++) {
            Point point = new Point(this.point.getA() + (i * direction.getDeltaX()),
                    this.point.getB() + (i * direction.getDeltaY()));
            if(game.getGrid().boardPositionIsValid(point)) {
                Player player = game.getGrid().getBoard().get(point).getA();
                if(player != null) {
                    player.decrementEnergy(player.getClassPlayer().getPenaltyShoot());
                }
            }

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
     * @return a list of point where it's possible to shot.
     * We add a point to the list where there is a player depending on the direction and the distance the weapon can
     * shoot.
     * @see Action#getValidPoint()
     */
    @Override
    public List<Point> getValidPoint() {
        List<Point> listMoves = new ArrayList<>();
        Point position = game.getCurrentPlayer().getPosition();
        Weapon weapon = game.getCurrentPlayer().getWeapon();
        for(Direction8Axis direction : Direction8Axis.values()) {
            Point neighbour = seeNeighbour(position, direction.getDeltaX(), direction.getDeltaY(),
                    direction.isVertical() ? weapon.getVerticalDistance() : weapon.getHorizontalDistance());
            if(neighbour != null)
                listMoves.add(neighbour);
        }
        return listMoves;
    }

    /**
     *
     * @param point player current position
     * @param deltaX given by {@link Direction4Axis}
     * @param deltaY given by {@link Direction4Axis}
     * @param range given by {@link Weapon#getHorizontalDistance()} or {@link Weapon#getVerticalDistance()}
     * @return true if there is a player in the chosen direction, false otherwise
     */
    public Point seeNeighbour(Point point, int deltaX, int deltaY, int range) {
        if(range == 0)
            return null;
        for(int i = 0; i < range; i++) {
            Point neighbour = new Point(point.getA() + deltaX + (i * deltaX), point.getB() + deltaY + (i * deltaY));
            if(game.getGrid().boardPositionIsValid(neighbour)) {
                if(game.getGrid().getBoard().get(neighbour).getB() instanceof Wall) {
                    return null;
                }
                if(game.getGrid().getBoard().get(neighbour).getA() instanceof Player) {
                    System.out.println(game.getGrid().getBoard().get(neighbour).getA().getPosition());
                    return neighbour;
                }
            }
        }
        return null;
    }

    public Point getPoint() {
        return point;
    }
}
