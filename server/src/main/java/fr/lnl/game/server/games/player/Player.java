package fr.lnl.game.server.games.player;

import fr.lnl.game.server.games.action.Action;
import fr.lnl.game.server.games.action.ReunionSameAction;
import fr.lnl.game.server.games.weapon.Weapon;
import fr.lnl.game.server.utils.Point;

import java.util.List;

public interface Player {

    List<ReunionSameAction> generateAvailableActions();

    Point getPosition();

    /**
     *
     * @return true if this player is Alive, false otherwise
     */
    boolean isAlive();

    int getId();

    int getEnergy();

    Weapon getWeapon();

    /**
     *
     * @return true if this player has his shield activated, false otherwise
     */
    boolean isShieldDeploy();

    void setEnergy(int energy);

    void setShieldDeploy(boolean shieldDeploy);

    void setWeapon(Weapon weapon);

    List<Action> getActions();

    void setActions(List<Action> actions);

    ClassPlayer getClassPlayer();

    void setPosition(Point position);

    /**
     *
     * Call by the implementing classes {@link fr.lnl.game.server.games.action.Action} to withdraw
     * a certain number of energy from a player
     */
    void decrementEnergy(int energy);

    /**
     *
     * Call by the implementing classes {@link fr.lnl.game.server.games.action.Action} to add
     * a certain number of energy from a player
     */
    void incrementEnergy(int energy);

}
