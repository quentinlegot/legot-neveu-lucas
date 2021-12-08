package fr.lnl.game.server.games.player;

import fr.lnl.game.server.games.action.Action;
import fr.lnl.game.server.games.action.ReunionSameAction;
import fr.lnl.game.server.games.weapon.Weapon;
import fr.lnl.game.server.utils.Point;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPlayer implements Player {

    private final int id;
    private Point position;
    private int energy;
    private Weapon weapon;
    private boolean shieldDeploy;
    private List<Action> actions;
    private final ClassPlayer classPlayer;

    public AbstractPlayer(Integer id, Point position, boolean shieldDeploy, ClassPlayer classPlayer) {
        this.id = id;
        this.classPlayer = classPlayer;
        this.energy = classPlayer.getEnergy();
        this.weapon = classPlayer.getWeapon();
        this.shieldDeploy = shieldDeploy;
        this.position = position;
    }

    @Override
    public List<ReunionSameAction> generateAvailableActions() {
        List<ReunionSameAction> actions = new ArrayList<>();
        for (Action a : getActions()) {
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

    @Override
    public boolean isAlive(){
        return energy > 0;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    @Override
    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public boolean isShieldDeploy() {
        return shieldDeploy;
    }

    @Override
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    @Override
    public void setShieldDeploy(boolean shieldDeploy) {
        this.shieldDeploy = shieldDeploy;
    }

    @Override
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public List<Action> getActions() {
        return actions;
    }

    @Override
    public void setActions(List<Action> actions){
        this.actions = actions;
    }

    @Override
    public ClassPlayer getClassPlayer() {
        return classPlayer;
    }

    @Override
    public Point getPosition() {
        return position;
    }


    @Override
    public void setPosition(/* NotNull */ Point position){
        if(position == null){
            throw new IllegalArgumentException("Position is null");
        }
        this.position = position;
    }

    @Override
    public void decrementEnergy(int energy){
        if(!isShieldDeploy()){
            this.energy -= energy;
        }
        shieldDeploy = false;
    }

    @Override
    public void incrementEnergy(int energy){
        this.energy += energy;
    }
}
