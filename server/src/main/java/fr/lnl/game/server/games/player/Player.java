package fr.lnl.game.server.games.player;

import fr.lnl.game.server.games.action.Action;
import fr.lnl.game.server.games.weapon.Weapon;
import fr.lnl.game.server.utils.Point;

import java.util.List;

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
    List<Action> getActions();
    void setActions(List<Action> actions);
    Action choseAction();
    ClassPlayer getClassPlayer();
    void setPoint(Point point);
    void decrementEnergy(int energy);
    void incrementEnergy(int energy);

}
