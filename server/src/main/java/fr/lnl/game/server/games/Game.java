package fr.lnl.game.server.games;

import fr.lnl.game.server.games.action.*;
import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.listener.AwakeGame;
import fr.lnl.game.server.listener.ModelListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Game {

    private final Grid grid;
    private final List<Player> players;
    private Player currentPlayer;
    private InterfaceAction interfaceAction;
    private Action selectedAction = null;
    private final Object lock = new Object();

    public Game(Grid grid, List<Player> players) throws IllegalArgumentException {
        if(players.size() < 2)
            throw new IllegalArgumentException("The game need 2 or more player to start");
        this.players = players;
        this.currentPlayer = players.get(0);
        this.grid = grid;
    }
    
    public void play() {
        while(!isOver()) {
            ModelListener awakeEvent = new AwakeGame(this);
            generateAndGetPlayerActions(currentPlayer);
            interfaceAction = InterfaceAction.SELECT_ACTION;
            waitForInterfaceEvent();
            selectedAction.doAction();
            nextCurrentPlayer();
        }
    }

    private void waitForInterfaceEvent() {
        synchronized (lock){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void resumeThread() {
        synchronized (lock) {
            lock.notifyAll();
        }
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

    public Stream<Player> getPlayersAlive() {
        return players.parallelStream().filter(Player::isAlive);
    }

    public boolean isOver() {
        return getPlayersAlive().count() <= 1;
    }

    public Player getWinner() {
        // On part du principe que isOver est forcément appelé avant d'appeler getWinner
        return getPlayersAlive().findFirst().orElse(null);
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

    public InterfaceAction getInterfaceAction() {
        return interfaceAction;
    }
}