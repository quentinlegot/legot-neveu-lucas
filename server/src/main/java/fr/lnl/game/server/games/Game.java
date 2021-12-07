package fr.lnl.game.server.games;

import fr.lnl.game.server.games.action.*;
import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.grid.build.GridFactoryBuilder;
import fr.lnl.game.server.games.grid.elements.CountdownBox;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.listener.AbstractModelListening;
import fr.lnl.game.server.listener.GameFinishEvent;
import fr.lnl.game.server.listener.ModelListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

public class Game {

    private final GridFactoryBuilder buildStrategy;
    private final Grid grid;
    private final List<Player> players;
    private final ModelListener gameFinishEvent;
    private final AbstractModelListening displayWinnerEvent;
    private Player currentPlayer;
    private Action selectedAction = null;

    public Game(GridFactoryBuilder buildStrategy, List<Player> players, AbstractModelListening displayWinnerEvent) throws IllegalArgumentException {
        this.grid = buildStrategy.build();
        if(players.size() < 2)
            throw new IllegalArgumentException("The game need 2 or more player to start");
        if(players.size() > grid.getNumberNeutralBox()){
            throw new IllegalArgumentException("There are too many players for the number of box available");
        }
        this.buildStrategy = buildStrategy;
        this.players = players;
        this.currentPlayer = players.get(0);
        this.gameFinishEvent = new GameFinishEvent(this);
        this.displayWinnerEvent = displayWinnerEvent;
        initGame();
    }

    public void initGame(){
        buildStrategy.initPlacePlayers();
        currentPlayer.setActions(generateAndGetPlayerActions(currentPlayer));
    }

    public void play() {
        selectedAction = currentPlayer.choseAction();
        selectedAction.doAction();
        countdownGridElementsUpdate();
        gridPlayersUpdate();
        nextCurrentPlayer();
        currentPlayer.setActions(generateAndGetPlayerActions(currentPlayer));
        if(isOver()) {
            gameFinishEvent.updateModel(null);
        }
    }

    private void gridPlayersUpdate(){
        for (Player player: getPlayersNotAlive().toList()) {
            getGrid().getBoard().get(player.getPosition()).setA(null);
        }
    }

    private void countdownGridElementsUpdate() {
        List<CountdownBox> countdownBoxes = this.getGrid().getAllCountdownElements();
        countdownBoxes.forEach(CountdownBox::update);
    }

    public List<Action> generateAndGetPlayerActions(Player player) {
        List<Action> actions = new ArrayList<>();
        for(Direction direction : Direction.values()) {
            try {
                actions.add(new Move(this, player, direction));
            } catch (NotValidDirectionException ignored){}
            try {
                actions.add(new DropBomb(this, player, direction));
            } catch (NotValidDirectionException ignored) {}
            try {
                actions.add(new DropMine(this, player, direction));
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

    public Stream<Player> getPlayersNotAlive() {
        return players.parallelStream().filter(not(Player::isAlive));
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
    public void nextCurrentPlayer() {
        do {
            int index = players.indexOf(currentPlayer) + 1;
            if(index == players.size())
                index = 0;
            setCurrentPlayer(players.get(index));
        } while(!currentPlayer.isAlive()); // On arrête la boucle dès qu'on trouve un joueur en vie
        currentPlayer.setShieldDeploy(false); // on reset son état
    }

    public void setCurrentPlayer(Player current_player) {
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

    public AbstractModelListening getDisplayWinnerEvent() {
        return displayWinnerEvent;
    }
}