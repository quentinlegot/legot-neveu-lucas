package fr.lnl.game.server;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.grid.EnergyBall;
import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.grid.Wall;
import fr.lnl.game.server.games.player.ClassPlayer;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.games.player.RandomComputerPlayer;
import fr.lnl.game.server.utils.Cardinal;
import fr.lnl.game.server.utils.Point;

import java.util.Arrays;
import java.util.List;

public class Mock {

    Grid grid;
    Game game;

    public Mock() {
        List<Player> players = Arrays.asList(new RandomComputerPlayer(1,null, ClassPlayer.DEFAULT),
                new RandomComputerPlayer(2,null, ClassPlayer.DEFAULT));
        this.grid = new Grid(16,16, players);
        placePlayersBRUT();
        placeEnergyBallBRUT();
        placeInternWallBRUT();
        game = new Game(grid, players);
    }

    public void placePlayersBRUT(){
        grid.getBoard().get(new Point(7,7)).setA(grid.getPlayers().get(0));
        grid.getPlayers().get(0).setPoint(new Point(7, 7));
        grid.getBoard().get(new Point(7,8)).setA(grid.getPlayers().get(1));
        grid.getPlayers().get(1).setPoint(new Point(7, 8));
    }

    public void placeEnergyBallBRUT(){
        grid.getBoard().get(new Point(2,3)).setB(new EnergyBall());
        grid.getBoard().get(new Point(8,10)).setB(new EnergyBall());
    }

    public void placeInternWallBRUT(){
        grid.getBoard().get(new Point(3,6)).setB(new Wall(Cardinal.NORTH,3,6));
        grid.getBoard().get(new Point(7,14)).setB(new Wall(Cardinal.SOUTH,7,14));
        grid.getBoard().get(new Point(10,7)).setB(new Wall(Cardinal.EAST,10,7));
        grid.getBoard().get(new Point(14,2)).setB(new Wall(Cardinal.WEST,14,2));
    }

}
