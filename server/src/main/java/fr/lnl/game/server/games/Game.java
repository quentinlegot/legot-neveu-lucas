package fr.lnl.game.server.games;

import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.player.Player;

import java.util.List;

public class Game {

    Grid grid;
    Player currentPlayer;
    List<Player> players;

    public Game(Grid grid, List<Player> players) throws IllegalArgumentException {
        if(players.size() < 2)
            throw new IllegalArgumentException("The game need 2 or more player to start");
        this.players = players;
        this.currentPlayer = players.get(0);
        this.grid = grid;
    }

    public void play() {

    }

    public boolean isOver() {
        return players.parallelStream().filter(player -> !player.isAlive()).count() == 1;
    }

    public Player getWinner() {
        // On part du principe que isOver est forcément appelé avant d'appeler getWinner
        return players.parallelStream().filter(player -> !player.isAlive()).findFirst().orElse(null);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Change player to the next available in the list
     */
    public boolean nextCurrentPlayer() {
        if(isOver())
            return false;
        do {
            int index = players.indexOf(currentPlayer) + 1;
            if(index == players.size())
                index = 0;
            currentPlayer = players.get(index);
        } while(!currentPlayer.isAlive()); // On arrête la boucle dès qu'on trouve un joueur en vie
        currentPlayer.setShieldDeploy(false); // on reset son état
        return true;
    }

    public void setCurrent_player(Player current_player) {
        this.currentPlayer = current_player;
    }

    public Grid getGrid() {
        return grid;
    }

    public List<Player> getPlayers() {
        return players;
    }
}