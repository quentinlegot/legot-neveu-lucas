package fr.lnl.game.server.games.player;

import fr.lnl.game.server.games.weapon.Weapon;
import fr.unicaen.utils.Point;

public abstract class AbstractPlayer {

    private int id;
    private Point position;
    private int ernergy;
    private Weapon weapon;
    private boolean shieldDeploy;

    public AbstractPlayer(int id, Point position, int ernergy, Weapon weapon, boolean shieldDeploy) {
        this.id = id;
        this.position = position;
        this.ernergy = ernergy;
        this.weapon = weapon;
        this.shieldDeploy = shieldDeploy;
    }

    public boolean isAlive(){
        return true;
    }

    public int getId() {
        return id;
    }

    public Point getPosition() {
        return position;
    }

    public int getErnergy() {
        return ernergy;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public boolean isShieldDeploy() {
        return shieldDeploy;
    }

    public void setErnergy(int ernergy) {
        this.ernergy = ernergy;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setShieldDeploy(boolean shieldDeploy) {
        this.shieldDeploy = shieldDeploy;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
