package fr.lnl.game.server.games.action;

/**
 * Enum used to chose where to move, shot, etc.
 */
public enum Direction4Axis implements Direction {

    UP(-1, 0, true),
    DOWN(1, 0, true),
    LEFT(0, -1, false),
    RIGHT(0, 1, false);

    private final int deltaX;
    private final int deltaY;
    private final boolean isVertical;

    Direction4Axis(int i, int i1, boolean isVertical) {
        this.deltaX = i;
        this.deltaY = i1;
        this.isVertical = isVertical;
    }

    @Override
    public int getDeltaX() {
        return deltaX;
    }

    @Override
    public int getDeltaY() {
        return deltaY;
    }

    /**
     * Used by {@link Shot} to know if the direction is in vertical direction cause shot action can have a different
     * distance depending on the direction
     * @return true if the direction is vertical
     */
    public boolean isVertical() {
        return isVertical;
    }
}
