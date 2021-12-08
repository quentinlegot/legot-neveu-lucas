package fr.lnl.game.server.mock;

import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.grid.build.GridFactoryBuilder;
import fr.lnl.game.server.games.grid.build.LockGridFactoryBuilder;
import fr.lnl.game.server.games.grid.elements.EnergyBall;
import fr.lnl.game.server.games.grid.elements.Wall;
import fr.lnl.game.server.utils.Point;

public class MockGridFactoryBuilder extends LockGridFactoryBuilder {

    private MockGridFactoryBuilder() {

    }

    public static GridFactoryBuilder create() {
        return new MockGridFactoryBuilder();
    }

    @Override
    protected void initPlaceEnergyBall() {
        grid.getBoard().get(new Point(2,3)).setB(new EnergyBall());
        grid.getBoard().get(new Point(8,10)).setB(new EnergyBall());
    }

    @Override
    protected void initPlaceInternWall() {
        grid.getBoard().get(new Point(3,6)).setB(new Wall(3,6));
        grid.getBoard().get(new Point(7,14)).setB(new Wall(7,14));
        grid.getBoard().get(new Point(10,7)).setB(new Wall(10,7));
        grid.getBoard().get(new Point(14,2)).setB(new Wall(14,2));
    }

    @Override
    public void initPlacePlayers() {
        grid.getBoard().get(new Point(7,7)).setA(grid.getPlayers().get(0));
        grid.getPlayers().get(0).setPosition(new Point(7, 7));
        grid.getBoard().get(new Point(7,8)).setA(grid.getPlayers().get(1));
        grid.getPlayers().get(1).setPosition(new Point(7, 8));
    }
}
