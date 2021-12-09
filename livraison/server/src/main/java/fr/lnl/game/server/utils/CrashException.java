package fr.lnl.game.server.utils;

/**
 * Represent an unrecoverable error in the program, force program to stop
 */
public class CrashException extends RuntimeException {

    public CrashException(String message, Throwable cause) {
        super(message, cause);
    }



}
