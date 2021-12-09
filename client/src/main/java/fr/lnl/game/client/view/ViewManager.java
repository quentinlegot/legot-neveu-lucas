package fr.lnl.game.client.view;

import fr.lnl.game.client.ClientPlayer;
import fr.lnl.game.client.ViewLambda;
import fr.lnl.game.client.listener.DisplayWinnerEvent;
import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.player.HumanPlayer;
import fr.lnl.game.server.games.player.Player;

import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public final class ViewManager {
    private final Game game;
    private final Class<? extends View> viewType;

    public ViewManager(Game game, Class<? extends View> viewType, ViewLambda lambda) {
        this.game = game;
        this.viewType = viewType;
        for (Player player : game.getPlayers()) {
            players.put(player, new ClientPlayer(player, lambda.createViewLambda(player)));
        }
    }


    public HashMap<Player, ClientPlayer> players = new HashMap<>();

    public void updateView() {
        players.get(game.getCurrentPlayer()).getView().show();
    }

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

    public void displayWinner(Player winner) {
        players.get(game.getCurrentPlayer()).getView().displayWinner(winner);
    }

    public void run() {
        if (viewType == Terminal.class) {
            terminalView();
        } else {
            updateView();
        }
    }

    public Game game() {
        return game;
    }

    public Class<? extends View> viewType() {
        return viewType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ViewManager) obj;
        return Objects.equals(this.game, that.game) &&
                Objects.equals(this.viewType, that.viewType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(game, viewType);
    }

    @Override
    public String toString() {
        return "ViewManager[" +
                "game=" + game + ", " +
                "viewType=" + viewType + ']';
    }

}
