package fr.lnl.game.server.games.action;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.player.AbstractPlayer;

public class DropBomb extends DropObject {

    public DropBomb(Game game){
        super(game);
    }

    @Override
    public void doAction() {

    }

    @Override
    public boolean isPossible() {
        return false;
    }
}
