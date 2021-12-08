package fr.lnl.game.server.games.grid.elements;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Point;

/**
 * Bomb are elements which explode when someone walks on it or after a countdown, the explosion area is on multiple cases
 */
public class Bomb extends Explosive implements CountdownBox {

    /**
     * Position of the bomb
     */
    private final Point point;
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
        super(game.getCurrentPlayer());
        this.point = point;
        this.game = game;
        counter = counter * game.getPlayers().size();
    }

    /**
     * Decrement players energy around this element
     * @param grid Game's grid
     * @param player the player who walks on this element
     * @param position position of this element on the grid
     * @see InteractiveBox#interact(Grid, Player, Point)
     * @see Explosive#interact(Grid, Player, Point)
     */
    @Override
    public void interact(Grid grid, /* Nullable */ Player player, Point position) {
        if(player != null)
            player.decrementEnergy(player.getClassPlayer().getPenaltyBomb());
        super.interact(grid, player, position);
    }

    /**
     * When the timer (counter) goes down to 0, the bomb explode
     * @see CountdownBox#update()
     */
    @Override
    public void update() {
        Grid grid = game.getGrid();
        counter--;
        if(counter == 0) {
            for(int i = -EXPLOSION_SIZE; i < EXPLOSION_SIZE; i++) {
                for(int j = -EXPLOSION_SIZE; j < EXPLOSION_SIZE; j++) {
                    if(pythagoras(i, j) <= EXPLOSION_SIZE) { // recherche en cercle, pas en carré
                        Point position = new Point(point.getA() + i, point.getB() + j);
                        if(position.getA() >= 0 && position.getA() < grid.getRow()
                                && position.getB() >= 0 && position.getB() < grid.getColumn()) {
                            Player player = grid.getBoard().get(position).getA();
                            interact(grid, player, position);
                        }
                    }
                }
            }
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
