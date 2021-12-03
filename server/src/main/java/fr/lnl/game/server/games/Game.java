package fr.lnl.game.server.games;

import fr.lnl.game.server.games.action.*;
import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.player.ComputerPlayer;
import fr.lnl.game.server.games.player.HumanPlayer;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.listener.ModelListener;
import fr.lnl.game.server.utils.CrashException;
import fr.lnl.game.server.utils.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Game {

    private final Grid grid;
    private final List<Player> players;
    private final ModelListener gameFinishEvent;
    private Player currentPlayer;
    private Action selectedAction = null;

    public Game(Grid grid, List<Player> players, ModelListener gameFinishEvent) throws IllegalArgumentException {
        if(players.size() < 2)
            throw new IllegalArgumentException("The game need 2 or more player to start");
        if(players.size() > grid.getNumberNeutralBox()){
            throw new IllegalArgumentException("There are too many players for the number of box available");
        }
        this.players = players;
        this.currentPlayer = players.get(0);
        this.grid = grid;
        this.gameFinishEvent = gameFinishEvent;
        this.grid.initPlacePlayers();
        currentPlayer.setActions(generateAndGetPlayerActions(currentPlayer));
    }

    public void play() {
        if (currentPlayer instanceof ComputerPlayer player) {
            selectedAction = player.choseAction();
        }
        selectedAction.doAction();
        nextCurrentPlayer();
        currentPlayer.setActions(generateAndGetPlayerActions(currentPlayer));
        if(isOver()) {
            gameFinishEvent.updateModel(null);
        }

    }

    private void waitForInterfaceEvent() {
        synchronized (this){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new CrashException(e.getMessage(), e);
            }
        }

    }

    public void resumeThread() {
        synchronized (this) {
            notifyAll();
        }
    }

    public List<Action> generateAndGetPlayerActions(Player player) {
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

    public Action getSelectedAction() {
        return selectedAction;
    }
}