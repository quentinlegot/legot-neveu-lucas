package fr.lnl.game.server.games.action;

import fr.lnl.game.server.games.Game;

public class DropMine extends DropObject {
    public DropMine(Game game){
        super(game);
    }
    @Override
    public void doAction() {
        super.doAction();
    }

    @Override
    public boolean isPossible() {
        return false;
    }
}
