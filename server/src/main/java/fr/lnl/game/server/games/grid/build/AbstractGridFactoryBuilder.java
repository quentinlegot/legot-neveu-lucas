package fr.lnl.game.server.games.grid.build;

import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.player.Player;

import java.util.List;

public abstract class AbstractGridFactoryBuilder implements GridFactoryBuilder {

    protected float wallProbability, energyProbability;
    protected Grid grid;
    private int row;
    private int columns;
    private List<Player> players;

    protected AbstractGridFactoryBuilder() {

    }

    public GridFactoryBuilder wallProbability(float probability) {
        this.wallProbability = probability;
        return this;
    }

    public GridFactoryBuilder energyProbability(float probability) {
        this.energyProbability = probability;
        return this;
    }


    public GridFactoryBuilder gridDimensions(int row, int columns) {
        this.row = row;
        this.columns = columns;
        return this;
    }


    public GridFactoryBuilder playersList(List<Player> players) {
        this.players = players;
        return this;
    }

    @Override
    public Grid build() {
        this.grid = new Grid(row, columns, players);
        initGrid();
        initPlaceInternWall(wallProbability);
        initPlaceEnergyBall(energyProbability);
        return grid;
    }

    protected Grid getGrid() {
        return grid;
    }

    protected abstract void initGrid();
    protected abstract void initPlaceEnergyBall(float probability);
    protected abstract void initPlaceInternWall(float probability);



}
