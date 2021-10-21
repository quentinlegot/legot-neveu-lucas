package fr.lnl.game.server.games.action;

import fr.lnl.game.server.games.Game;

public class DeployShield extends AbstractAction {
    public DeployShield(Game game){
        super(game);
    }

    @Override
    public void doAction(){

    }

    @Override
    public boolean isPossible() {
        return true;
    }


}

