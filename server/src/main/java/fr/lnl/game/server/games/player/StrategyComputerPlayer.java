package fr.lnl.game.server.games.player;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.action.*;
import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.grid.elements.Box;
import fr.lnl.game.server.games.grid.elements.EnergyBall;
import fr.lnl.game.server.games.grid.elements.Explosive;
import fr.lnl.game.server.utils.Point;

import java.util.List;
import java.util.Random;

public class StrategyComputerPlayer extends ComputerPlayer {

    public StrategyComputerPlayer(Integer id, Point point, ClassPlayer classPlayer) {
        super(id,point, classPlayer);
    }

    @Override
    public Action strategy(Game game) {
        Action action = null;
        Grid grid = game.getGrid();
        for (Player player : game.getPlayers()) {
            boolean danger = player.getActions().stream().anyMatch(a -> a instanceof Shot && a.getPoint().equals(getPosition()));
            if(danger && (getEnergy() - getClassPlayer().getPenaltyShoot() <= 0)){
                action = new DeployShield(this);
            }
        }
        List<ReunionSameAction> actions = generateAvailableActions();
        if(isInReunion(actions, Shot.class)){
            ReunionSameAction reunion = extractReunionSameAction(actions, Shot.class);
            List<Action> actionList = reunion.getActions();
            action = actionList.get(0);
            if(actionList.size() > 1){
                for (int i = 1; i < actionList.size(); i++) {
                    Point point = actionList.get(i).getPoint();
                    if(grid.getGridPlayer(point).getEnergy() < grid.getGridPlayer(action.getPoint()).getEnergy()){
                        action = actionList.get(i);
                    }
                }
            }
            return action;
        }
        if(isInReunion(actions, Move.class)){
            ReunionSameAction reunion = extractReunionSameAction(actions, Move.class);
            List<Action> actionList = reunion.getActions();
            for (Action value : actionList) {
                Point point = value.getPoint();
                Box box = grid.getGridBox(point);
                if(box instanceof EnergyBall){
                    return value;
                }
                System.out.println("after move " + action);
            }
            Random random = new Random();
            int value = random.nextInt(0,2);
            System.out.println(value);
            if(value == 0){
                System.out.println("oui");
                do{
                    action = actionList.get(random.nextInt(0, actionList.size()));
                    Box box = game.getGrid().getGridBox(action.getPoint());
                    if(box instanceof Explosive) {
                        if (!((Explosive) box).getPlayer().equals(this)) {
                            action = null;
                        }
                    }
                }while(action == null);
                return action;
            }
            if(isInReunion(actions, Explosive.class)){
                List<Action> explosiveActions = extractReunionSameAction(actions, Move.class).getActions();
                if(explosiveActions.size() > 1){
                    action = explosiveActions.get(random.nextInt(0, explosiveActions.size()));
                    return action;
                }
                System.out.println("explo " + action);
            }
        }
        if(isInReunion(actions, Explosive.class)){
            Random random = new Random();
            List<Action> explosiveActions = extractReunionSameAction(actions, Move.class).getActions();
            if(explosiveActions.size() > 1){
                action = explosiveActions.get(random.nextInt(0, explosiveActions.size()));
            }
            return action;
        }
        else {
            action = new Nothing();
            System.out.println("nothing " + action);
        }
        System.out.println("end " + action);
        return action;
    }

    public boolean isInReunion(List<ReunionSameAction> actions, Class clazz){
        return actions.stream().anyMatch(r -> r.getActionName().equals(clazz.getSimpleName()));
    }

    public ReunionSameAction extractReunionSameAction(List<ReunionSameAction> actions, Class clazz){
        return actions.stream().filter(r -> r.getActionName().equals(clazz.getSimpleName())).findFirst().get();
    }

    @Override
    public String toString() {
        return "AI";
    }
}
