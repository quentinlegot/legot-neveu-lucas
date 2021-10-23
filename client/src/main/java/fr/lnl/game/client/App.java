package fr.lnl.game.client;
import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.player.ClassPlayer;
import fr.lnl.game.server.games.player.ComputerPlayer;
import fr.lnl.game.server.games.player.Player;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

public class App extends Application {

    public static void main(String[] args) {
        Class<? extends AbstractView> clazz = parseArgs(args);
        if(clazz.equals(Terminal.class)) {
            launchTerminal();
        } else {
            launch();
        }
    }

    public static Game startGame() {
        List<Player> players = Arrays.asList(new ComputerPlayer(1, null, ClassPlayer.DEFAULT),
                new ComputerPlayer(1, null, ClassPlayer.DEFAULT));
        return new Game(new Grid(12, 12, players), players);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Game game = startGame();
        Window window = new Window(stage, game);
        // TODO: 23/10/2021 faire une view par joueur
        window.show();
    }

    public static void launchTerminal() {
        Game game = startGame();
        Terminal terminal = new Terminal(game);
        // TODO: 23/10/2021 faire une view par joueur
    }

    // TODO: 23/10/2021 nécessite un rework -> faire une view par joueur
    public static Class<? extends AbstractView> parseArgs(String[] args) {
        if(args.length >= 1) {
            if(args[0].equalsIgnoreCase("terminal")) {
                return Terminal.class;
            } else {
                return Window.class;
            }
        } else {
            return Window.class;
        }
    }
}
