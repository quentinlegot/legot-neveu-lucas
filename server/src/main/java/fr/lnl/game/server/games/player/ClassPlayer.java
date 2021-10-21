package fr.lnl.game.server.games.player;

import fr.lnl.game.server.games.weapon.Firearm;
import fr.lnl.game.server.games.weapon.Weapon;

public enum ClassPlayer {

    DEFAULT(800, 25, 40, 30, 40, 10, 800, 20, 20, 15, new Firearm());

    private final int energy;
    private final int shieldCost;
    private final int shootCost;
    private final int mineCost;
    private final int bombCost;
    private final int moveCost;
    private final int gainEnergyCost;
    private final int penaltyShoot;
    private final int penaltyBomb;
    private final int penaltyMine;
    private final Weapon weapon;


    ClassPlayer(int energy, int shieldCost, int shootCost, int mineCost, int bombCost, int moveCost,
                int gainEnergyCost, int penaltyShoot, int penaltyBomb, int penaltyMine, Weapon weapon){
        this.energy = energy;
        this.shieldCost = shieldCost;
        this.shootCost = shootCost;
        this.mineCost = mineCost;
        this.bombCost = bombCost;
        this.moveCost = moveCost;
        this.gainEnergyCost = gainEnergyCost;
        this.penaltyShoot = penaltyShoot;
        this.penaltyBomb = penaltyBomb;
        this.penaltyMine = penaltyMine;
        this.weapon = weapon;
    }

    public int getEnergy() {
        return energy;
    }

    public int getShieldCost() {
        return shieldCost;
    }

    public int getShootCost() {
        return shootCost;
    }

    public int getMineCost() {
        return mineCost;
    }

    public int getBombCost() {
        return bombCost;
    }

    public int getMoveCost() {
        return moveCost;
    }

    public int getGainEnergyCost() {
        return gainEnergyCost;
    }

    public int getPenaltyShoot() {
        return penaltyShoot;
    }

    public int getPenaltyBomb() {
        return penaltyBomb;
    }

    public int getPenaltyMine() {
        return penaltyMine;
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
