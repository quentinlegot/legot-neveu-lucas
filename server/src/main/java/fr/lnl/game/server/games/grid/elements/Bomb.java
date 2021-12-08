package fr.lnl.game.server.games.grid.elements;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Point;

public class Bomb extends Explosive implements CountdownBox {

    private final Point point;
    private final Game game;
    private int counter = 3;
    private static int EXPLOSION_SIZE = 4;

    public Bomb(Point point, Game game) {
        super(game.getCurrentPlayer());
        this.point = point;
        this.game = game;
        counter = counter * game.getPlayers().size();
    }

    @Override
    public void interact(Grid grid, /* Nullable */ Player player, Point position) {
        if(player != null)
            player.decrementEnergy(player.getClassPlayer().getPenaltyBomb());
        super.interact(grid, player, position);
    }

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

    public double pythagoras(double a, double b) {
        return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    }
}
