package fr.lnl.game.server.utils;

import java.util.Scanner;

public class Maths {

    /**
     * Convert entry to an integer
     * @param entry given parameter
     * @param scanner standard input, used if entry isn't correct
     * @param error error message to display if entry isn't an Integer
     * @return an Integer if entry is valid or when it'll valid
     */
    public static int testInteger(String entry, Scanner scanner, String error) {
        while (!isInteger(entry)) {
            System.out.println(ErrorMessage.Entry_Error_Message + error);
            entry = scanner.next();
        }
        return Integer.parseInt(entry);
    }

    /**
     * Convert entry to a float
     * @param entry given parameter
     * @param scanner standard input, used if entry isn't correct
     * @param error error message to display if entry isn't a float
     * @return a float if entry is a valid or when it'll valid
     */
    public static float testFloat(String entry, Scanner scanner, String error) {
        while (!isFloat(entry)) {
            System.out.println(ErrorMessage.Entry_Error_Message + error);
            entry = scanner.next();
        }
        return Integer.parseInt(entry);
    }

    /**
     * @param strNum entry
     * @return true if {@code strNum} is a float, false otherwise
     * @see Maths#testFloat(String, Scanner, String)
     */
    public static boolean isFloat(String strNum) {
        try {
            float d = Float.parseFloat(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    /**
     * @param strNum entry
     * @return true if {@code strNum} is an Integer, false otherwise
     */
    public static boolean isInteger(String strNum) {
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
}
