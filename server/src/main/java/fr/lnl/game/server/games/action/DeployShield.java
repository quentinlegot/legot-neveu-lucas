package fr.lnl.game.server.games.action;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Point;

import java.util.List;

public class DeployShield extends AbstractAction {
    public DeployShield(Game game, Player player){
        super(game, player);
    }

    @Override
    public void doAction(){
        Player player = getGame().getCurrentPlayer();
        player.setShieldDeploy(true);
        player.decrementEnergy(player.getClassPlayer().getShieldCost());
    }

    @Override
    public boolean isPossible() {
        return true;
    }

    @Override
    public List<Point> getValidPoint() {
        return null;
    }


}

