package fr.lnl.game.client;

import fr.lnl.game.client.view.AbstractView;
import fr.lnl.game.client.view.Terminal;
import fr.lnl.game.client.view.ViewManager;
import fr.lnl.game.client.view.Window;
import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.grid.build.GridFactoryBuilder;
import fr.lnl.game.server.games.grid.build.LockGridFactoryBuilder;
import fr.lnl.game.server.games.player.*;
import fr.lnl.game.server.utils.CrashException;
import fr.lnl.game.server.utils.Point;
import javafx.application.Application;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Application starting point
 */
public class App extends Application {

    private static LinkedList<String> argsList;
    private static Game game;
    private static ViewManager viewManager;

    public static void main(String[] args) {
         try {
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
         } catch(CrashException e) {
             System.err.println("Une erreur est survenue et l'application a été obligé de fermer");
             System.err.println(e.getCause().toString());
             for (StackTraceElement element : e.getStackTrace()) {
                 System.err.println("   at " + element.toString());
             }
             System.exit(1);
         }
    }

    /**
     * Parse players arguments and create a new instance of Game
     * @throws IllegalArgumentException when given argument is unknown
     * @throws InvocationTargetException when the creation of the player throw an exception
     * @throws NoSuchMethodException when constructor with given parameter in {@link Class#getConstructor(Class[])}
     * doesn't exist
     * @throws InstantiationException when the instanciation of the player is impossible (like when class is abstract),
     * is probably never called
     * @throws IllegalAccessException when the instanciation of thr player is impossible (like a private constructor),
     * is probably never called
     */
    public static void startGame() throws IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        List<Player> players = parsePlayers();
        GridFactoryBuilder builder = LockGridFactoryBuilder.create().energyProbability(0.95F).wallProbability(0.80F).gridDimensions(12, 12);
        game = new Game(builder, players);
    }

    @Override
    public void start(Stage stage) {
        try {
            startGame();
        } catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | InstantiationException
                | IllegalAccessException e) {
            throw new CrashException(e.getMessage(), e);
        }
        viewManager = new ViewManager(game, Window.class, player -> new Window(stage, game, player));
        viewManager.run();
    }

    public static void launchTerminal() {
        try {
            startGame();
        } catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | InstantiationException
                | IllegalAccessException e) {
            throw new CrashException(e.getMessage(), e);
        }
        viewManager = new ViewManager(game, Terminal.class, player -> new Terminal(game, player));
        viewManager.run();
    }

    /**
     * Parse players arguments and create instances for each player
     * @throws IllegalArgumentException when given argument is unknown
     * @throws InvocationTargetException when the creation of the player throw an exception
     * @throws NoSuchMethodException when constructor with given parameter in {@link Class#getConstructor(Class[])}
     * doesn't exist
     * @throws InstantiationException when the instanciation of the player is impossible (like when class is abstract),
     * is probably never called
     * @throws IllegalAccessException when the instanciation of thr player is impossible (like a private constructor),
     * is probably never called
     */
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
                case "computerS" -> {
                    if(playerClass != null) {
                        playerList.add(createNewPlayer(playerClass,
                                classPlayer != null ? classPlayer : ClassPlayer.DEFAULT, playerList.size())
                        );
                        classPlayer = null;
                    }
                    playerClass = StrategyComputerPlayer.class;
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

    /**
     * create a new instance of the player
     * @throws IllegalArgumentException when given argument is unknown (probably never called in production)
     * @throws InvocationTargetException when the creation of the player throw an exception
     * @throws NoSuchMethodException when constructor with given parameter in {@link Class#getConstructor(Class[])}
     * doesn't exist
     * @throws InstantiationException when the instanciation of the player is impossible (like when class is abstract),
     * is probably never called
     * @throws IllegalAccessException when the instanciation of thr player is impossible (like a private constructor),
     * is probably never called
     */
    private static Player createNewPlayer(Class<? extends AbstractPlayer> playerClass, ClassPlayer playerType,
                                          int playerListSize) throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        return playerClass.getConstructor(Integer.class, Point.class, ClassPlayer.class)
                .newInstance(playerListSize, null, playerType);
    }

    /**
     * Parse the first argument given by user to know the view to use (Terminal or Window)
     * @return The class of the View to use
     * @throws IllegalArgumentException when given argument is unknown or no argument is given by user
     */
    public static Class<? extends AbstractView> parseView() throws IllegalArgumentException {
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

    public static ViewManager getViewManager() {
        return viewManager;
    }

    public static Game getGame() {
        return game;
    }
}
