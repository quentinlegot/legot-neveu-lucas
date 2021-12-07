package fr.lnl.game.server.games.grid.build;

import fr.lnl.game.server.games.grid.Grid;

public abstract class AbstractBuildStrategy implements BuildStrategy{

    private final float WALL_PROBABILITY, ENERGY_PROBABILITY;
    protected final Grid grid;

    public AbstractBuildStrategy(Grid grid, float wallProbability, float energyProbability){
        this.grid = grid;
        this.WALL_PROBABILITY = wallProbability;
        this.ENERGY_PROBABILITY = energyProbability;
        build();
    }

    @Override
    public void build() {
        initGrid();
        initPlaceInternWall(WALL_PROBABILITY);
        initPlaceEnergyBall(ENERGY_PROBABILITY);
    }

    private float getEnergyProbability() {
        return ENERGY_PROBABILITY;
    }

    private float getWallProbability() {
        return WALL_PROBABILITY;
    }

    public Grid getGrid() {
        return grid;
    }

    protected abstract void initGrid();
    protected abstract void initPlaceEnergyBall(float probability);
    protected abstract void initPlaceInternWall(float probability);



}
