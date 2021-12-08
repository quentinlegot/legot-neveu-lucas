package fr.lnl.game.server.games.grid.elements;

/**
 * A wall is an intraversable object
 */
public class Wall extends AbstractBox {

    /**
     * Used by tests
     * @param obj the object to compare
     * @return true if obj is an instance of wall, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Wall;
    }
}
