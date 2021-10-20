package fr.lnl.game.server.games.player;

import fr.lnl.game.server.games.action.Action;
import fr.lnl.game.server.games.weapon.Weapon;
import fr.lnl.game.server.utils.Point;

public abstract class AbstractPlayer implements Player {

    private int id;
    private int energy;
    private Weapon weapon;
    private boolean shieldDeploy;
    private Action[] actions;
    private ClassPlayer classPlayer;

    public AbstractPlayer(int id, boolean shieldDeploy, ClassPlayer classPlayer) {
        this.id = id;
        this.classPlayer = classPlayer;
        this.energy = classPlayer.getEnergy();
        this.weapon = classPlayer.getWeapon();
        this.shieldDeploy = shieldDeploy;
    }

    public boolean isAlive(){
        return true;
    }

    public int getId() {
        return id;
    }

    public int getEnergy() {
        return energy;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public boolean isShieldDeploy() {
        return shieldDeploy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setShieldDeploy(boolean shieldDeploy) {
        this.shieldDeploy = shieldDeploy;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Action[] getActions() {
        return actions;
    }

    public ClassPlayer getClassPlayer() {
        return classPlayer;
    }
}
