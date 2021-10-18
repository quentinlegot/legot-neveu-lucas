package fr.lnl.game.server;

import fr.lnl.game.server.games.grid.Grid;

public class ServerMain {

    public static void main(String[] args) {
        Grid grid = new Grid(10,10);
        grid.initGrid();
        grid.printGrid();
    }
}
