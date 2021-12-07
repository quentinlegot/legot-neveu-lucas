package fr.lnl.game.server.games.player;

import fr.lnl.game.server.games.action.Action;
import fr.lnl.game.server.utils.Maths;
import fr.lnl.game.server.utils.Point;
import java.util.Scanner;

public class HumanPlayer extends AbstractPlayer {

    public HumanPlayer(Integer id, Point point, ClassPlayer classPlayer) {
        super(id, point,false, classPlayer);
    }

    @Override
    public Action choseAction() {
        String error = "Veuillez renseigner une valeur numérique comprise entre 1 et " + getActions().size();
        Action action = null;
        Scanner scanner = new Scanner(System.in);
        do{
            System.out.println("Choisissez une action :");
            for (int i = 0; i < getActions().size(); i++) {
                System.out.println((i + 1) + " : " + getActions().get(i).getClass().getSimpleName());
            }
            String entry = scanner.next();
            int value = Maths.testInteger(entry,scanner,error);
            if(value >= 1 && value <= getActions().size()){
                action = getActions().get(value - 1);
            }
        }while(action == null);
        return action;
    }
}
