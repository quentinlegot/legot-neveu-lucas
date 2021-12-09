package fr.lnl.game.client.view;

import fr.lnl.game.client.ClientPlayer;
import fr.lnl.game.client.ViewLambda;
import fr.lnl.game.client.listener.DisplayWinnerEvent;
import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.player.HumanPlayer;
import fr.lnl.game.server.games.player.Player;

import java.util.HashMap;
import java.util.Scanner;

/**
 * View manager, main access to every views
 */
public final class ViewManager {

    private final Game game;
    private final Class<? extends View> viewType;
    public HashMap<Player, ClientPlayer> players = new HashMap<>();

    public ViewManager(Game game, Class<? extends View> viewType, ViewLambda lambda) {
        this.game = game;
        this.viewType = viewType;
        for (Player player : game.getPlayers()) {
            players.put(player, new ClientPlayer(player, lambda.createViewLambda(player)));
        }
    }

    /**
     * Call when we need to change or update view
     */
    public void updateView() {
        players.get(game.getCurrentPlayer()).getView().show();
    }

    /**
     * This method is call when the view is a terminal
     */
    public void terminalView() {
        Terminal.scanner = new Scanner(System.in);
        DisplayWinnerEvent displayWinnerEvent = new DisplayWinnerEvent();
        while (true) {
            Player player = game.getCurrentPlayer();
            System.out.println("\n\033[0;34m====== Tour n°" + game.getNbrTurn() + " =======\033[0m");
            System.out.println("\nA \033[0;31m" + player + " " + player.getId() + "\033[0m de jouer");
            players.get(game.getCurrentPlayer()).getView().show();
            if (game.getCurrentPlayer() instanceof HumanPlayer human) {
                game.setSelectedAction(((Terminal) players.get(human).getView()).choseAction());
            }
            boolean isOver = game.play();
            System.out.println("\n\033[0;31m" + player + " " + player.getId() + "\033[0m utilise l'action \033[0;36m" +
                    game.getSelectedAction().getClass().getSimpleName() + "\033[0m");
            if (isOver) {
                displayWinnerEvent.updateModel(game.getWinner());
                Terminal.scanner.close();
                System.exit(0);
            }
        }
    }

    /**
     * This method is call when the game is finish
     * @param winner The winner of the game, can be null
     */
    public void displayWinner(Player winner) {
        players.get(game.getCurrentPlayer()).getView().displayWinner(winner);
    }

    /**
     * This method is call after initialized view manager to display a first screen
     */
    public void run() {
        if (viewType == Terminal.class) {
            terminalView();
        } else {
            updateView();
        }
    }

}
