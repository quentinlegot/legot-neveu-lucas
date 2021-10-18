package fr.lnl.game.server;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.player.ComputerPlayer;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.games.weapon.Firearm;

public class ServerMain {

    public static void main(String[] args) {
        Player playerOne = new ComputerPlayer(1,1000,new Firearm());
        Player playerTwo = new ComputerPlayer(2,1000,new Firearm());

        Grid grid = new Grid(16,16,new Player[]{playerOne,playerTwo});
        grid.initGrid();
        grid.placePlayersBRUT();
        grid.placeEnergyBallBRUT();
        grid.placeInternWallBRUT();
        Game game = new Game(grid,playerOne,playerTwo);
        game.getGrid().printGrid();
    }
}
