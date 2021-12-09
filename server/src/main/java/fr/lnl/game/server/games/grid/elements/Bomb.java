package fr.lnl.game.server.games.grid.elements;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Point;

/**
 * Bomb are elements which explode when someone walks on it or after a countdown, the explosion area is on multiple cases
 */
public class Bomb extends Explosive implements CountdownBox {

    private final Game game;
    /**
     * Timer before explosion
     */
    private int counter = 5;
    /**
     * Explosion size, size is circle, not square
     */
    private static final int EXPLOSION_SIZE = 2;

    public Bomb(Point point, Game game) {
        super(game.getCurrentPlayer(), point);
        this.game = game;
        counter = counter * game.getPlayers().size();
    }

    protected void explode(Grid grid) {
        for(int i = -EXPLOSION_SIZE; i < EXPLOSION_SIZE; i++) {
            for(int j = -EXPLOSION_SIZE; j < EXPLOSION_SIZE; j++) {
                if(pythagoras(i, j) <= EXPLOSION_SIZE) { // recherche en cercle, pas en carré
                    Point position = new Point(point.getA() + i, point.getB() + j);
                    if(grid.boardPositionIsValid(position)) {
                        Player player = grid.getBoard().get(position).getA();
                        if(player != null)
                            player.decrementEnergy(player.getClassPlayer().getPenaltyBomb());
                    }
                }
            }
        }
        super.explode(grid);
    }

    /**
     * When the timer (counter) goes down to 0, the bomb explode
     * @see CountdownBox#update()
     */
    @Override
    public void update() {
        counter--;
        if(counter == 0) {
            explode(game.getGrid());
        }
    }

    /**
     * @param a adjacent side of a triangle
     * @param b opposite side of a triangle
     * @return Pythagoras' theorem value
     */
    public double pythagoras(double a, double b) {
        return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    }
}
