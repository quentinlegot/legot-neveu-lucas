package fr.lnl.game.server.games.grid;

import fr.lnl.game.server.utils.Cardinal;

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
}
