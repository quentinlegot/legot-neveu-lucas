package fr.lnl.game.server.listener;

import fr.lnl.game.server.games.Game;

public class AwakeGame extends AbstractModelListening {

    private final Game game;

    public AwakeGame(Game game) {
        this.game = game;
    }

    @Override
    public void updateModel(Object obj) {
        game.resumeThread();
    }
}
