package fr.lnl.game.server.games.action;

public enum Direction8Axis implements Direction {

    UP(-1, 0, true),
    DOWN(1, 0, true),
    LEFT(0, -1, false),
    RIGHT(0, 1, false),
    UP_LEFT(-1, -1, true),
    UP_RIGHT(-1, 1, true),
    DOWN_RIGHT(1, 1, true),
    DOWN_LEFT(1, -1, true);

    private final int deltaX;
    private final int deltaY;
    private final boolean isVertical;

    Direction8Axis(int i, int i1, boolean isVertical) {
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
     * distance depending of the direction
     * @return true if the direction is vertical
     */
    public boolean isVertical() {
        return isVertical;
    }


}
