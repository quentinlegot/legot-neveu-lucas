package fr.lnl.game.server.games.grid.build;

import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.player.Player;

import java.util.List;

/**
 * Factory pattern
 */
public interface GridFactoryBuilder {

    /**
     * Set energy probability<br>
     * @param probability energy probability, which will be used by
     * {@link AbstractGridFactoryBuilder#initPlaceEnergyBall()} when calling {@link GridFactoryBuilder#build()}
     * @return {@code this}
     */
    GridFactoryBuilder energyProbability(float probability);

    /**
     * Set wall probability<br>
     * @param probability wall probability, which will be used by
     * {@link AbstractGridFactoryBuilder#initPlaceInternWall()} when calling {@link GridFactoryBuilder#build()}
     * @return {@code this}
     */
    GridFactoryBuilder wallProbability(float probability);

    /**
     * set grid dimensions.
     * Arguments are given when instancing {@link Grid} when using {@link GridFactoryBuilder#build()}
     * @param row row grid's size
     * @param columns columns grid's size
     * @return {@code this}
     */
    GridFactoryBuilder gridDimensions(int row, int columns);

    /**
     * set players list.
     * Argument given to {@link Grid} when calling {@link GridFactoryBuilder#build()}
     * @param players list a players
     * @return {@code this}
     */
    GridFactoryBuilder playersList(List<Player> players);

    /**
     * Call this method after you call {{@link GridFactoryBuilder#energyProbability(float)}},
     * {@link GridFactoryBuilder#wallProbability(float)}, {@link GridFactoryBuilder#gridDimensions(int, int)} and
     * {@link GridFactoryBuilder#playersList(List)}, It'll instantiate a new {@link Grid} and initialize his components
     * like border walls, intern walls, and energy using parameters given previously
     * @return an instance of {@link Grid}
     */
    Grid build();

    /**
     * call when initializing the game, it'll place player depending on the strategy used by its implementation,
     * need to be call after build, call an NullPointerException otherwise
     */
    void initPlacePlayers();

}
