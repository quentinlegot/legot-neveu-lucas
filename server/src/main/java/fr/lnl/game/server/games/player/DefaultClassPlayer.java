package fr.lnl.game.server.games.player;

import fr.lnl.game.server.games.weapon.Firearm;
import fr.lnl.game.server.games.weapon.Weapon;

@SuppressWarnings("all")

public class DefaultClassPlayer implements ClassPlayer{

    private final int ENERGY = 800;
    private final int SHIELDCOST = 25;
    private final int SHOOTDCOST = 40;
    private final int MINECOST = 30;
    private final int BOMBCOST = 40;
    private final int DEPLACECOST = 10;
    private final int GAINENERGYBALL = 800;
    private final int PENALTYSHOOT = 20;
    private final int PENALTYBOMB = 20;
    private final int PENALTYMINE = 15;
    private final Weapon WEAPON = new Firearm();


    public DefaultClassPlayer(){
    }

    @Override
    public int getEnergy() {
        return this.ENERGY;
    }

    @Override
    public int getShieldCost() {
        return this.SHIELDCOST;
    }

    @Override
    public int getShootdCost() {
        return this.SHOOTDCOST;
    }

    @Override
    public int getMineCost() {
        return this.MINECOST;
    }

    @Override
    public int getBombCost() {
        return this.BOMBCOST;
    }

    @Override
    public int getDeplaceCost() {
        return this.DEPLACECOST;
    }

    @Override
    public int getGainEnergyBall() {
        return this.GAINENERGYBALL;
    }

    @Override
    public int getPenaltyShoot() {
        return this.PENALTYSHOOT;
    }

    @Override
    public int getPenaltyMine() {
        return this.PENALTYMINE;
    }

    @Override
    public int getPenaltyBomb() {
        return this.PENALTYBOMB;
    }

    @Override
    public Weapon getWeapon() {
        return this.WEAPON;
    }
}
