package fr.lnl.game.server.utils;

public class CrashException extends RuntimeException {

    public CrashException(String message, Throwable cause) {
        super(message, cause);
        System.exit(1);
    }



}
