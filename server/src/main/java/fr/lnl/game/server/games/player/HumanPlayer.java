package fr.lnl.game.server.games.player;

import fr.lnl.game.server.games.action.*;
import fr.lnl.game.server.utils.Maths;
import fr.lnl.game.server.utils.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HumanPlayer extends AbstractPlayer {

    private List<ReunionSameAction> actions;

    public HumanPlayer(Integer id, Point point, ClassPlayer classPlayer) {
        super(id, point,false, classPlayer);
    }

    @Override
    // TODO: 07/12/2021 Retirer les scanners pour respecter le MVC
    public Action choseAction() {
        actions = new ArrayList<>();
        for (Action a : getActions()) {
            ReunionSameAction reunionFilter = actions.stream().filter(r -> r.getActionName().equals(a.getClass().getSimpleName())).findFirst().orElse(null);
            if(reunionFilter != null){
                reunionFilter.addAction(a);
            }
            else{
                actions.add(new ReunionSameAction(a.getClass().getSimpleName(),a));
            }
        }
        List<Action> listActions = choseReunionSameAction().getActions();
        Action action = null;
        String error = "Veuillez renseigner une valeur numérique comprise entre 1 et " + listActions.size();
        Scanner scanner = new Scanner(System.in);
        do{
            if(listActions.size() == 1){
                return listActions.get(0);
            }
            System.out.println("Choisissez la cible :");
            for (int i = 0; i < listActions.size(); i++) {
                Action a = listActions.get(i);
                if(a instanceof Move){
                    System.out.println(i + 1 + " : " +  ((Move) a).getDirection());
                }
                else if(a instanceof DropObject){
                    System.out.println(i + 1 + " : " +  ((DropObject) a).getDirection());
                }
                else if(a instanceof Shot){
                    System.out.println(i + 1 + " : " + ((Shot) a).getPoint());
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
        }while (action == null);
        return action;
    }

    public ReunionSameAction choseReunionSameAction(){
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
