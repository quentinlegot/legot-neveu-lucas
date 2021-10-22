package fr.lnl.game.server.games.action;

import fr.lnl.game.server.games.Game;

public class DeployShield extends AbstractAction {
    public DeployShield(Game game){
        super(game);
    }

    @Override
    public void doAction(){
        getGame().getCurrentPlayer().setShieldDeploy(true);
        getGame().getCurrentPlayer().decrementEnergy(getGame().getCurrentPlayer().getClassPlayer().getShieldCost());
    }

    @Override
    public boolean isPossible() {
        return true;
    }


}

