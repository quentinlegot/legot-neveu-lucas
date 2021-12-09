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
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StrategyComputerPlayer extends ComputerPlayer {

    public StrategyComputerPlayer(Integer id, Point point, ClassPlayer classPlayer) {
        super(id,point, classPlayer);
    }

    @Override
    public Action strategy(Game game) {
        Action deployShield = choseDeployShield(game);
        if(deployShield != null){
            return deployShield;
        }

        List<ReunionSameAction> actions = generateAvailableActions();
        Action shot = choseShot(actions,game);
        if(shot != null){
            return shot;
        }
        Action move = choseMove(actions,game);
        if(move != null){
            return move;
        }
        return choseExplosive(actions);
    }

    private Action choseExplosive(List<ReunionSameAction> actions){
        Random random = new Random();
        if(isInReunion(actions, DropBomb.class) || isInReunion(actions, DropMine.class)){
            List<Action> explosiveActions = extractReunionSameAction(actions, DropMine.class).getActions();
            explosiveActions.addAll(extractReunionSameAction(actions, DropBomb.class).getActions());
            return explosiveActions.get(random.nextInt(0, explosiveActions.size()));
        }
        else{
            return new Nothing();
        }
    }

    private Action choseDeployShield(Game game){
        for (Player player : game.getPlayers()) {
            List<Point> shot = new Shot(game,player).getValidPoint();
            boolean danger = shot.stream().anyMatch(p -> p.equals(getPosition()));
            if(danger && (getEnergy() - getClassPlayer().getPenaltyShoot() <= 0)){
                return new DeployShield(this);
            }
        }
        return null;
    }

    private Action choseShot(List<ReunionSameAction> actions, Game game){
        if(isInReunion(actions, Shot.class)) {
            ReunionSameAction reunion = extractReunionSameAction(actions, Shot.class);
            List<Action> actionList = reunion.getActions();
            Action action = actionList.get(0);
            if (actionList.size() > 1) {
                for (int i = 1; i < actionList.size(); i++) {
                    Point point = actionList.get(i).getPoint();
                    if (game.getGrid().getGridPlayer(point).getEnergy() < game.getGrid().getGridPlayer(action.getPoint()).getEnergy()) {
                        action = actionList.get(i);
                    }
                }
            }
            return action;
        }
        return null;
    }

    private Action choseMove(List<ReunionSameAction> actions, Game game){
        if(isInReunion(actions, Move.class)) {
            ReunionSameAction reunion = extractReunionSameAction(actions, Move.class);
            List<Action> actionList = reunion.getActions();
            for (Action value : actionList) {
                Point point = value.getPoint();
                Box box = game.getGrid().getGridBox(point);
                if (box instanceof EnergyBall) {
                    return value;
                }
            }
            Random random = new Random();
            int value = random.nextInt(0, 2);
            if (value == 0) {
                Action action = null;
                do {
                    Action move = actionList.get(random.nextInt(0, actionList.size()));
                    Box box = game.getGrid().getGridBox(move.getPoint());
                    if (box instanceof Explosive) {
                        if (!(((Explosive) box).getPlayer().equals(this))) {
                            action = move;
                        }
                        else{
                            if(actionList.stream().filter(a -> game.getGrid().getGridBox(a.getPoint()) instanceof Explosive).toList().size() ==
                                    actionList.size()){
                                action = move;
                            }
                        }
                    }
                    else{
                        action = move;
                    }
                } while (action == null);
                return action;
            }
            else{
                return choseExplosive(actions);
            }
        }
        return null;
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
