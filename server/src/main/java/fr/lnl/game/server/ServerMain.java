package fr.lnl.game.server;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.player.ComputerPlayer;
import fr.lnl.game.server.games.player.DefaultClassPlayer;
import fr.lnl.game.server.games.player.Player;

public class ServerMain {

    public static void main(String[] args) {
        Player playerOne = new ComputerPlayer(1,null,new DefaultClassPlayer());
        Player playerTwo = new ComputerPlayer(2,null,new DefaultClassPlayer());

        Grid grid = new Grid(16,16,new Player[]{playerOne,playerTwo});
        grid.initGrid();
        grid.placePlayersBRUT();
        grid.placeEnergyBallBRUT();
        grid.placeInternWallBRUT();
        Game game = new Game(grid,playerOne,playerTwo);
        game.getGrid().printGrid();
    }
}
