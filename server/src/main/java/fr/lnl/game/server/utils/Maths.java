package fr.lnl.game.server.utils;

import java.util.Scanner;

public class Maths {

    public static int testInteger(String entry, Scanner scanner, String error) {
        while (!isNumeric(entry)) {
            System.out.println(Error.Entry_Error_Message + error);
            entry = scanner.next();
        }
        return Integer.parseInt(entry);
    }

    public static float testFloat(String entry, Scanner scanner) {
        while (!isFloat(entry)) {
            System.out.println("\033[0;31mErreur de saisie\033[0m : Veuillez rentrer une valeur correcte !");
            entry = scanner.next();
        }
        return Integer.parseInt(entry);
    }

    public static boolean isFloat(String strNum) {
        try {
            float d = Float.parseFloat(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isNumeric(String strNum) {
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
}
