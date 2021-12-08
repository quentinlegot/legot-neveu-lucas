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

    /**
     * @param probability wall probability, which will be used by
     * {@link AbstractGridFactoryBuilder#initPlaceInternWall()} when calling {@link GridFactoryBuilder#build()}
     * @return {@code this}
     * @see GridFactoryBuilder#wallProbability(float)
     */
    public GridFactoryBuilder wallProbability(float probability) {
        this.wallProbability = probability;
        return this;
    }

    /**
     * @param probability energy probability, which will be used by
     * {@link AbstractGridFactoryBuilder#initPlaceEnergyBall()} when calling {@link GridFactoryBuilder#build()}
     * @return {@code this}
     * @see GridFactoryBuilder#energyProbability(float)
     */
    public GridFactoryBuilder energyProbability(float probability) {
        this.energyProbability = probability;
        return this;
    }

    /**
     *
     * @param row row grid's size
     * @param columns columns grid's size
     * @return {@code this}
     * @see GridFactoryBuilder#gridDimensions(int, int)
     */
    public GridFactoryBuilder gridDimensions(int row, int columns) {
        this.row = row;
        this.columns = columns;
        return this;
    }

    /**
     * @param players list a players
     * @return {@code this}
     * @see GridFactoryBuilder#playersList(List)
     */
    public GridFactoryBuilder playersList(List<Player> players) {
        this.players = players;
        return this;
    }

    /**
     * Call this method after you call {{@link GridFactoryBuilder#energyProbability(float)}},
     * {@link GridFactoryBuilder#wallProbability(float)}, {@link GridFactoryBuilder#gridDimensions(int, int)} and
     * {@link GridFactoryBuilder#playersList(List)}. It'll instantiate a new {@link Grid} and initialize his components
     * like border walls, intern walls, and energy using parameters given previously
     * @return an instance of {@link Grid}
     */
    @Override
    public Grid build() {
        this.grid = new Grid(row, columns, players);
        initGrid();
        initPlaceInternWall();
        initPlaceEnergyBall();
        return grid;
    }

    protected Grid getGrid() {
        return grid;
    }

    /**
     * abstract method used to initialize grid<br>
     * We let implementation of this class the way to initialize the grid
     */
    protected abstract void initGrid();

    /**
     * abstract method used to place energy ball<br>
     * We let implementation of this class the way to initialize the grid<br>
     * Implementation need to use {@link AbstractGridFactoryBuilder#energyProbability} to place energy balls
     */
    protected abstract void initPlaceEnergyBall();

    /**
     * abstract method used to place intern walls(namely not border walls)<br>
     * We let implementation of this class the way to initialize the grid.<br>
     * Implementation need to use {@link AbstractGridFactoryBuilder#wallProbability} to place energy balls
     */
    protected abstract void initPlaceInternWall();



}
