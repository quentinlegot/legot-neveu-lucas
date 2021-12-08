package fr.lnl.game.server.utils;

/**
 * Contain everything related to error messages, theirs errors are the fault of the end-user (like we demand an integer
 * and user give us a floating point number
 * Theirs error doesn't cause the program to stop, it'll demand a correct value
 */
public class ErrorMessage {

    /**
     * Error given to end-user when it give us a non integer or float value
     */
    public static final String Entry_Error_Message = "\033[0;31mErreur de saisie\033[0m : ";
}
