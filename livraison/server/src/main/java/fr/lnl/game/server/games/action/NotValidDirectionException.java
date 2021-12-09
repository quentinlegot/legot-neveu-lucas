package fr.lnl.game.server.games.action;

/**
 * throw when action instantiated and the chosen direction in constructor isn't valid
 */
public class NotValidDirectionException extends Exception {

    public NotValidDirectionException(String message) {
        super(message);
    }

}
