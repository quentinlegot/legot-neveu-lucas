package fr.lnl.game.server.games;

import fr.lnl.game.server.games.action.*;
import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.grid.build.GridFactoryBuilder;
import fr.lnl.game.server.games.grid.elements.CountdownBox;
import fr.lnl.game.server.games.player.ComputerPlayer;
import fr.lnl.game.server.games.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

public class Game {

    private final Grid grid;
    private final List<Player> players;
    private Player currentPlayer;
    private Action selectedAction = null;

    /**
     * @param buildStrategy used to build a grid
     * @param players a list players in the game
     * @throws IllegalArgumentException if number of players given in parameters is too many or inferior to 2
     */
    public Game(GridFactoryBuilder buildStrategy, List<Player> players) throws IllegalArgumentException {
        this.grid = buildStrategy.playersList(players).build();
        if(players.size() < 2)
            throw new IllegalArgumentException("The game need 2 or more player to start");
        if(players.size() > grid.getNumberNeutralBox()){
            throw new IllegalArgumentException("There are too many players for the number of box available");
        }
        this.players = players;
        this.currentPlayer = players.get(0);
        initGame(buildStrategy);
    }

    /**
     * Initialize a game by placing players on the grid and by generating current player available actions
     * @param buildStrategy builder used to create a grid
     */
    public void initGame(GridFactoryBuilder buildStrategy){
        buildStrategy.initPlacePlayers();
        currentPlayer.setActions(generateAndGetPlayerActions(currentPlayer));
    }

    /**
     * Game "main" method, call by a controller after human chose an action or when a computer player play an action
     * Method is call everytime an action has been chosen by a human or when aa computer player need to play
     * @return true if game is over, false otherwise
     */
    public boolean play() {
        if(currentPlayer instanceof ComputerPlayer computer)
            // si le joueur est humain alors le choix se fait avant l'appel de play()
            selectedAction = computer.choseAction(this);
        selectedAction.doAction();
        countdownGridElementsUpdate();
        gridPlayersUpdate();
        nextCurrentPlayer();
        currentPlayer.setActions(generateAndGetPlayerActions(currentPlayer));
        return isOver();
    }

    /**
     * Remove dead players from the grid
     */
    private void gridPlayersUpdate(){
        for (Player player: getPlayersNotAlive().toList()) {
            getGrid().getBoard().get(player.getPosition()).setA(null);
        }
    }

    /**
     * play grid's elements that use a timer like {@link fr.lnl.game.server.games.grid.elements.Bomb} at each game tick
     */
    private void countdownGridElementsUpdate() {
        List<CountdownBox> countdownBoxes = this.getGrid().getAllCountdownElements();
        countdownBoxes.forEach(CountdownBox::update);
    }

    /**
     * Used to list all actions a player can execute at current time
     * @param player the player to generate actions
     * @return a list of available actions
     */
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

    /**
     *
     * @return a list of alive players
     */
    public Stream<Player> getPlayersAlive() {
        return players.parallelStream().filter(Player::isAlive);
    }

    /**
     * Opposite of {@link Game#getPlayersAlive()}
     * @return a list of dead players
     */
    public Stream<Player> getPlayersNotAlive() {
        return players.parallelStream().filter(not(Player::isAlive));
    }

    /**
     * A game is over if the number of alive players is inferior to 2
     * @return true if game is over, false otherwise
     */
    public boolean isOver() {
        return getPlayersAlive().count() <= 1;
    }

    /**
     *
     * @return the winner of the game if exists, null otherwise.<br>
     * return the only remaining alive player when it exists, or null if everyone is dead, per example when a bomb kill
     * the 2 remaining players
     */
    public Player getWinner() {
        // On part du principe que isOver est forcément appelé avant d'appeler getWinner
        return getPlayersAlive().findFirst().orElse(null);
    }

    /**
     *
     * @return the player who is currently playing
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Change player to the next available in the list.<br>
     * We set its shield deploy state to false.
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

    /**
     * @param current_player the new current player
     */
    public void setCurrentPlayer(Player current_player) {
        this.currentPlayer = current_player;
    }

    public Grid getGrid() {
        return grid;
    }

    public List<Player> getPlayers() {
        return players;
    }

    /**
     *
     * @return action selected by current player if not already executed or last player if already executed
     */
    public Action getSelectedAction() {
        return selectedAction;
    }

    /**
     * @param selectedAction set the action selected by current player before doing it
     */
    public void setSelectedAction(Action selectedAction) {
        this.selectedAction = selectedAction;
    }
}