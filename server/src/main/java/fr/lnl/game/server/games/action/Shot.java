package fr.lnl.game.server.games.action;

import fr.lnl.game.server.games.Game;

public class Shot extends AbstractAction {
    public Shot(Game game) {
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
