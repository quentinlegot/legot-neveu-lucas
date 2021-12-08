package fr.lnl.game.server.games.grid.elements;

/**
 * A box implemented by CountdownBox is a box which do an action a certain time after being placed
 */
public interface CountdownBox {

    /**
     * Call at each game tick (After a player do an action)
     */
    void update();

}
