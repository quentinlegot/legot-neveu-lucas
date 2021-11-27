package fr.lnl.game.client;
import fr.lnl.game.client.view.AbstractView;
import fr.lnl.game.client.view.Terminal;
import fr.lnl.game.client.view.Window;
import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.player.*;
import fr.lnl.game.server.utils.CrashException;
import fr.lnl.game.server.utils.Point;
import javafx.application.Application;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class App extends Application {

    private static LinkedList<String> argsList;
    public static HashMap<Player, ClientPlayer> playerList = new HashMap<>();
    public static Game game;

    public static void main(String[] args) {
        argsList = new LinkedList<>(Arrays.asList(args));
        argsList.removeIf(s -> s.startsWith("-D") || s.equals("fr.lnl.game.client.App")); // remove given parameters from gradle
        Class<? extends AbstractView> clazz;
        try {
            clazz = parseView();
        } catch (IllegalArgumentException e) {
            throw new CrashException(e.getMessage(), e);
        }
        if(clazz.equals(Terminal.class)) {
            launchTerminal();
        } else {
            launch();
        }
    }

    public static void startGame() throws IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        List<Player> players = parsePlayers();
        game = new Game(new Grid(12, 12, players), players);
        for (Player player : game.getPlayers()) {
            playerList.put(player, new ClientPlayer(player, new Terminal(game, player)));
        }
    }

    public static void updateView() {
        App.playerList.get(App.game.getCurrentPlayer()).getView().show();
    }

    @Override
    public void start(Stage stage) {
        try {
            startGame();
        } catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | InstantiationException
                | IllegalAccessException e) {
            throw new CrashException(e.getMessage(), e);
        }
        new Thread(() -> game.play());
        updateView();
    }

    public static void launchTerminal() {
        try {
            startGame();
        } catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | InstantiationException
                | IllegalAccessException e) {
            throw new CrashException(e.getMessage(), e);
        }
        new Thread(() -> game.play());
        updateView();
    }

    public static List<Player> parsePlayers() throws IllegalArgumentException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        List<Player> playerList = new ArrayList<>();
        Class<? extends AbstractPlayer> playerClass = null;
        ClassPlayer classPlayer = null;
        for(String str : argsList) {
            switch (str) {
                case "human" ->  {
                    if(playerClass != null) {
                        playerList.add(createNewPlayer(playerClass,
                                classPlayer != null ? classPlayer : ClassPlayer.DEFAULT, playerList.size())
                        );
                        classPlayer = null;
                    }
                    playerClass = HumanPlayer.class;
                }
                case "computer" -> {
                    if(playerClass != null) {
                        playerList.add(createNewPlayer(playerClass,
                                classPlayer != null ? classPlayer : ClassPlayer.DEFAULT, playerList.size())
                        );
                        classPlayer = null;
                    }
                    playerClass = RandomComputerPlayer.class;
                }
                case "default" -> classPlayer = ClassPlayer.DEFAULT;
                case "tank" -> classPlayer = ClassPlayer.TANK;
                case "dps" -> classPlayer = ClassPlayer.DPS;
                case "support" -> classPlayer = ClassPlayer.SUPPORT;
                default -> throw new IllegalArgumentException("Unknown argument: " + str);
            }
        }
        if(playerClass != null)
            playerList.add(createNewPlayer(playerClass,
                    classPlayer != null ? classPlayer : ClassPlayer.DEFAULT, playerList.size())
            );
        return playerList;
    }

    private static Player createNewPlayer(Class<? extends AbstractPlayer> playerClass, ClassPlayer playerType,
                                          int playerListSize) throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        return playerClass.getConstructor(Integer.class, Point.class, ClassPlayer.class)
                .newInstance(playerListSize, null, playerType);
    }

    public static Class<? extends AbstractView> parseView() {
        Class<? extends  AbstractView> clazz;
        if(!argsList.isEmpty()) {
            if(argsList.get(0).equals("terminal")) {
                clazz = Terminal.class;
            } else if(argsList.get(0).equals("window")){
                clazz = Window.class;
            } else {
                throw new IllegalArgumentException("Unknown argument: " + argsList.get(0));
            }
            argsList.removeFirst();
        } else {
            throw new IllegalArgumentException("No argument given");
        }
        return clazz;
    }
}
