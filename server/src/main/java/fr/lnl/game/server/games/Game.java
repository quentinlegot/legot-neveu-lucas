package fr.lnl.game.server.games;

import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.player.Player;

import java.util.List;

public class Game {

    Grid grid;
    Player current_player;
    List<Player> players;

    public Game(Grid grid, List<Player> players) {
        if(players.size() < 2)
            throw new IllegalArgumentException("The game need 2 or more player to start");
        this.players = players;
        this.current_player = players.get(0);
        this.grid = grid;
    }

    public void play(){

    }

    public boolean isOver() {
        return players.parallelStream().filter(player -> !player.isAlive()).count() == 1;
    }

    public Player getWinner(){
        return players.parallelStream().filter(player -> !player.isAlive()).findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new);
    }

    public Player getCurrentPlayer() {
        return current_player;
    }

    public Grid getGrid() {
        return grid;
    }

    public List<Player> getPlayers() {
        return players;
    }
}