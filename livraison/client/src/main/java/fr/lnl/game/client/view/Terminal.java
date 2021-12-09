package fr.lnl.game.client.view;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.action.*;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Maths;

import java.util.List;
import java.util.Scanner;

/**
 * View terminal, use standard input and output
 */
public class Terminal extends AbstractView {

    /**
     * Standard input
     */
    public static Scanner scanner;

    public Terminal(Game game, Player player) {
        super(game, player);
    }

    /**
     * Used to update view
     */
    public void show() {
        System.out.println(game.getGrid().privateView(player));
    }

    /**
     * Used to display the winner of the game
     * @param winner the player who win the game, can be Null
     */
    @Override
    public void displayWinner(Player winner) {
        System.out.println("\n\033[0;31m====== FIN DU JEU ======\033[0m");
        System.out.println(game.getGrid().toString());
        if(winner == null) {
            System.out.println("\n\033[0;Partie nulle, personne n'a gagné la partie\033[0m");
        } else {
            System.out.println("\n\033[0;33mVictoire de " + winner  + " " + winner.getId() + "\033[0m");
        }

    }

    /**
     * Used when current player is an instance of {@link fr.lnl.game.server.games.player.HumanPlayer} and demand to it
     * an action to do
     * @return chosen action
     * @see Terminal#choseReunionSameAction(List)
     */
    public Action choseAction() {
       List<ReunionSameAction> actions = player.generateAvailableActions();
       List<Action> listActions = choseReunionSameAction(actions).getActions();
       Action action = null;
       String error = "Veuillez renseigner une valeur numérique comprise entre 1 et " + listActions.size();
       do {
           if(listActions.size() == 1){
               return listActions.get(0);
           }
           System.out.println("Choisissez la cible :");
           for (int i = 0; i < listActions.size(); i++) {
               Action a = listActions.get(i);
               if(a instanceof Move m){
                   System.out.println(i + 1 + " : " +  m.getDirection());
               }
               else if(a instanceof DropObject o){
                   System.out.println(i + 1 + " : " +  o.getDirection());
               }
               else if(a instanceof Shot s){
                   System.out.println(i + 1 + " : " + s.getPoint());
               }
               else{
                   System.out.println(i + 1 + " : " + a.getClass().getSimpleName());
               }
           }
           String entry = scanner.next();
           int value = Maths.testInteger(entry, scanner, error);
           if (value >= 1 && value <= listActions.size()) {
               action = listActions.get(value - 1);
           }
       } while (action == null);
       return action;
    }

    /**
     * Used when current player is an instance of {@link fr.lnl.game.server.games.player.HumanPlayer} and demand to it
     * a type of action to do
     * @param actions the list of actions possible
     * @return the type of action to execute
     * @see Terminal#choseAction()
     */
    private ReunionSameAction choseReunionSameAction(List<ReunionSameAction> actions) {
        ReunionSameAction reunion = null;
        String error = "Veuillez renseigner une valeur numérique comprise entre 1 et " + actions.size();
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Choisissez une action :");
            for (int i = 0; i < actions.size(); i++) {
                System.out.println(i + 1 + " : " + actions.get(i).getActionName());
            }
            String entry = scanner.next();
            int value = Maths.testInteger(entry, scanner, error);
            if (value >= 1 && value <= actions.size()) {
                reunion = actions.get(value - 1);
            }
        }while (reunion == null) ;
        return reunion;
    }

}
