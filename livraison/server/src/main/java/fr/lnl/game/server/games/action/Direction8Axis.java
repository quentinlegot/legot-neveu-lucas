package fr.lnl.game.server.games.action;

public enum Direction8Axis implements Direction {

    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1),
    UP_LEFT(-1, -1),
    UP_RIGHT(-1, 1),
    DOWN_RIGHT(1, 1),
    DOWN_LEFT(1, -1);

    private final int deltaX;
    private final int deltaY;

    Direction8Axis(int i, int i1) {
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
