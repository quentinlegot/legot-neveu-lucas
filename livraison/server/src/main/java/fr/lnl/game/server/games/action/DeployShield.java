package fr.lnl.game.server.games.action;

import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Point;

import java.util.List;

/**
 * Used when a player want to protect himself from taking damage (shield state is reset to false at next player turn)
 */
public class DeployShield extends AbstractAction {

    public DeployShield(Player player){
        super(null, player);
    }

    /**
     * Deploy player shield and decrement its energy
     */
    @Override
    public void doAction(){
        player.decrementEnergy(player.getClassPlayer().getShieldCost());
        player.setShieldDeploy(true);
    }

    /**
     * This action is always possible
     * @return always true
     */
    @Override
    public boolean isPossible() {
        return true;
    }

    @Override
    public Point getPoint() {
        return null;
    }

    @Override
    public List<Point> getValidPoint() {
        return null;
    }


}

