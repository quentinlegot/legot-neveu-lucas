package fr.lnl.game.client.view;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.action.Action;
import fr.lnl.game.server.games.action.ReunionSameAction;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Maths;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class AbstractView implements View {

    protected final Player player;
    protected Game game;

    public AbstractView(Game game, Player player) {
        this.game = game;
        this.player = player;
    }

    protected List<ReunionSameAction> generateAvailableActions() {
        List<ReunionSameAction> actions = new ArrayList<>();
        for (Action a : player.getActions()) {
            ReunionSameAction reunionFilter = actions.stream()
                    .filter(r -> r.getActionName().equals(a.getClass().getSimpleName()))
                    .findFirst()
                    .orElse(null);
            if(reunionFilter != null){
                reunionFilter.addAction(a);
            }
            else{
                actions.add(new ReunionSameAction(a.getClass().getSimpleName(),a));
            }
        }
        return actions;
    }

    protected ReunionSameAction choseReunionSameAction(List<ReunionSameAction> actions) {
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
