package fr.lnl.game.server;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.player.ComputerPlayer;
import fr.lnl.game.server.games.player.ClassPlayer;
import fr.lnl.game.server.games.player.Player;

import java.util.Arrays;
import java.util.List;

public class ServerMain {

    public static void main(String[] args) {
        List<Player> players = Arrays.asList(new ComputerPlayer(1,null, ClassPlayer.DEFAULT),
                new ComputerPlayer(2,null, ClassPlayer.DEFAULT));
        Grid grid = new Grid(16,16, players);
        grid.initGrid();
        grid.placePlayersBRUT();
        grid.placeEnergyBallBRUT();
        grid.placeInternWallBRUT();
        Game game = new Game(grid, players);
        game.getGrid().printGrid();
    }
}
