package fr.lnl.game.server.utils;

public enum Cardinal {
    NORTH,
    SOUTH,
    EAST,
    WEST,
    NORTH_EAST,
    NORTH_WEST,
    SOUTH_EAST,
    SOUTH_WEST;

    public static Cardinal getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}


