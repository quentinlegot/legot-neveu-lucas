package fr.lnl.game.server.games.player;

import fr.lnl.game.server.games.action.Action;
import fr.lnl.game.server.games.weapon.Weapon;
import fr.lnl.game.server.utils.Point;

import java.util.List;

public interface Player {

    Action choseAction();

    Point getPosition();

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

    ClassPlayer getClassPlayer();

    void setPosition(Point position);

    void decrementEnergy(int energy);

    void incrementEnergy(int energy);

}
