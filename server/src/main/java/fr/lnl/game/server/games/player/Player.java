package fr.lnl.game.server.games.player;

import fr.lnl.game.server.games.action.Action;
import fr.lnl.game.server.games.weapon.Weapon;
import fr.lnl.game.server.utils.Point;

public interface Player {
    Point getPoint();
    boolean isAlive();
    int getId();
    int getEnergy();
    Weapon getWeapon();
    boolean isShieldDeploy();
    void setEnergy(int energy);
    void setShieldDeploy(boolean shieldDeploy);
    void setWeapon(Weapon weapon);
    Action[] getActions();
    ClassPlayer getClassPlayer();
    void setPoint(Point point);
}
