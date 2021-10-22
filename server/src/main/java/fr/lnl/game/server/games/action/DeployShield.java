package fr.lnl.game.server.games.action;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.player.Player;

public class DeployShield extends AbstractAction {
    public DeployShield(Game game){
        super(game);
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


}

