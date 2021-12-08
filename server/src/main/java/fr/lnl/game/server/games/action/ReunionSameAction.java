package fr.lnl.game.server.games.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * When we generate action, we generate one per available direction (like in {@link Move}) but to improve human
 * readability, we list every same Action here
 */
public class ReunionSameAction {

    private final String actionName;
    private List<Action> actions;

    public ReunionSameAction(String actionName){
        this.actionName = actionName;
        this.actions = new ArrayList<>();
    }

    public ReunionSameAction(String actionName, Action action){
        this(actionName);
        this.actions = new ArrayList<>(List.of(action));
    }

    public List<Action> getActions() {
        return actions;
    }


    public String getActionName() {
        return actionName;
    }

    public Action getAction(int value) {
        return getActions().get(value);
    }

    public void addAction(Action action) {
        this.actions.add(action);
    }
}
