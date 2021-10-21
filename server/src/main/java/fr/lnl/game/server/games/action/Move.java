package fr.lnl.game.server.games.action;

import fr.lnl.game.server.games.Game;

public class Move extends AbstractAction {
    public Move(Game game) {
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
