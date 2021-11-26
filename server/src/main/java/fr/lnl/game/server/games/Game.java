package fr.lnl.game.server.games;

import fr.lnl.game.server.games.action.*;
import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
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

    protected List<Action> generateAndGetPlayerActions(Player player) {
        List<Action> actions = new ArrayList<>();
        for(Direction direction : Direction.values()) {
            try {
                actions.add(new Move(this, player, direction));
            } catch (NotValidDirectionException ignored){}
            try {
                new DropBomb(this, player, direction);
            } catch (NotValidDirectionException ignored) {}
            try {
                new DropMine(this, player, direction);
            } catch (NotValidDirectionException ignored) {}
            try {
                actions.add(new Shot(this, player, direction));
            } catch (NotValidDirectionException | NoMoreBulletInWeaponException ignored) {}
        }
        actions.addAll(Arrays.asList(new Nothing(), new DeployShield(player)));
        return actions;
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