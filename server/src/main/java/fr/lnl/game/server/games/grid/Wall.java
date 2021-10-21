package fr.lnl.game.server.games.grid;

import fr.lnl.game.server.utils.Cardinal;

import java.util.Objects;

public class Wall implements Box {

    private Cardinal cardinal;
    private int x;
    private int y;

    public Wall(Cardinal cardinal, int x, int y){
        this.cardinal = cardinal;
        this.x = x;
        this.y = y;
    }

    public Cardinal getCardinal() {
        return cardinal;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wall other = (Wall) o;
        return Objects.equals(cardinal, other.cardinal) && x == other.x && y == other.y;
    }
}
