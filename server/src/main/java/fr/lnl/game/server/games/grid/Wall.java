package fr.lnl.game.server.games.grid;

import java.util.Objects;

public class Wall extends AbstractBox {

    private final int x;
    private final int y;

    public Wall(int x, int y){
        this.x = x;
        this.y = y;
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
        return x == other.x && y == other.y;
    }
}
