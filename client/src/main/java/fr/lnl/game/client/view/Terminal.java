package fr.lnl.game.client.view;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.action.*;
import fr.lnl.game.server.games.player.HumanPlayer;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Maths;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Terminal extends AbstractView {

    public static Scanner scanner = new Scanner(System.in);

    public Terminal(Game game, Player player) {
        super(game, player);
    }

    public void show() {
        System.out.println(game.getGrid().toString());
    }

    @Override
    public void displayWinner(Player winner) {
        System.out.println("Le joueur " + winner + " a gagné la partie");
    }

    @Override
    public Action choseAction() {
       List<ReunionSameAction> actions = generateAvailableActions();
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

}
