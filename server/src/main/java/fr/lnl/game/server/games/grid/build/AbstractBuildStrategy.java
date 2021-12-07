package fr.lnl.game.server.games.grid.build;

import fr.lnl.game.server.games.grid.Grid;

public abstract class AbstractBuildStrategy implements BuildStrategy{

    private final float WALL_PROBABILITY, ENERGY_PROBABILITY;
    private final Grid GRID;

    public AbstractBuildStrategy(Grid grid, float wallProbability, float energyProbability){
        this.GRID = grid;
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
        return GRID;
    }

    abstract void initGrid();
    abstract void initPlaceEnergyBall(float probability);
    abstract void initPlaceInternWall(float probability);



}
