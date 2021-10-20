package fr.lnl.game.server.games.player;

import fr.lnl.game.server.games.weapon.Weapon;
import fr.lnl.game.server.utils.Point;

public interface ClassPlayer {

    int getEnergy();
    int getShieldCost();
    int getShootdCost();
    int getMineCost();
    int getBombCost();
    int getDeplaceCost();
    int getGainEnergyBall();
    int getPenaltyShoot();
    int getPenaltyMine();
    int getPenaltyBomb();
    Weapon getWeapon();
}