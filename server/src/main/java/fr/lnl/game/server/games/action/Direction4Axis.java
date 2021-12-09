package fr.lnl.game.server.games.action;

/**
 * Enum used to chose where to move, shot, etc.
 */
public enum Direction4Axis implements Direction {

    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1);

    private final int deltaX;
    private final int deltaY;

    Direction4Axis(int i, int i1) {
        this.deltaX = i;
        this.deltaY = i1;
    }

    @Override
    public int getDeltaX() {
        return deltaX;
    }

    @Override
    public int getDeltaY() {
        return deltaY;
    }
}
