package fr.lnl.game.server.games.player;

import fr.lnl.game.server.games.weapon.Firearm;
import fr.lnl.game.server.games.weapon.Weapon;

public enum ClassPlayer {

    DEFAULT(800, 25, 40, 30, 40, 10, 80, 20, 20, 15, new Firearm()),
    TANK(1000, 20, 20, 17, 23, 13, 80, 27, 30, 22, new Firearm()),
    DPS(800, 25, 16, 15, 20, 10, 80, 40, 40, 30, new Firearm()),
    SUPPORT(600, 25, 20, 11, 15, 7, 80, 45, 45, 35, new Firearm());
    private final int energy;
    private final int shieldCost;
    private final int shootCost;
    private final int mineCost;
    private final int bombCost;
    private final int moveCost;
    private final int gainEnergy;
    private final int penaltyShoot;
    private final int penaltyBomb;
    private final int penaltyMine;
    private final Weapon weapon;


    ClassPlayer(int energy, int shieldCost, int shootCost, int mineCost, int bombCost, int moveCost,
                int gainEnergy, int penaltyShoot, int penaltyBomb, int penaltyMine, Weapon weapon){
        this.energy = energy;
        this.shieldCost = shieldCost;
        this.shootCost = shootCost;
        this.mineCost = mineCost;
        this.bombCost = bombCost;
        this.moveCost = moveCost;
        this.gainEnergy = gainEnergy;
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

    public int getGainEnergy() {
        return gainEnergy;
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
