package fr.lnl.game.server;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.grid.build.BuildStrategy;
import fr.lnl.game.server.games.grid.build.LockStrategy;
import fr.lnl.game.server.games.grid.elements.EnergyBall;
import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.grid.elements.Wall;
import fr.lnl.game.server.games.player.ClassPlayer;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.games.player.RandomComputerPlayer;
import fr.lnl.game.server.listener.GameFinishEvent;
import fr.lnl.game.server.utils.Point;

import java.util.Arrays;
import java.util.List;

public class Mock {

    public BuildStrategy buildStrategy;
    public Game game;
    public Grid grid;

    public Mock() {
        List<Player> players = Arrays.asList(new RandomComputerPlayer(1,null, ClassPlayer.DEFAULT),
                new RandomComputerPlayer(2,null, ClassPlayer.DEFAULT));
        this.buildStrategy = new LockStrategy(new Grid(16,16, players),0.80F, 0.95F);
        game = new Game(buildStrategy, players, new GameFinishEvent());
        this.grid = buildStrategy.getGrid();
    }

    public void placePlayersBRUT(){
        grid.getBoard().get(new Point(7,7)).setA(grid.getPlayers().get(0));
        grid.getPlayers().get(0).setPosition(new Point(7, 7));
        grid.getBoard().get(new Point(7,8)).setA(grid.getPlayers().get(1));
        grid.getPlayers().get(1).setPosition(new Point(7, 8));
    }

    public void placeEnergyBallBRUT(){
        grid.getBoard().get(new Point(2,3)).setB(new EnergyBall());
        grid.getBoard().get(new Point(8,10)).setB(new EnergyBall());
    }

    public void placeInternWallBRUT(){
        grid.getBoard().get(new Point(3,6)).setB(new Wall(3,6));
        grid.getBoard().get(new Point(7,14)).setB(new Wall(7,14));
        grid.getBoard().get(new Point(10,7)).setB(new Wall(10,7));
        grid.getBoard().get(new Point(14,2)).setB(new Wall(14,2));
    }

}
