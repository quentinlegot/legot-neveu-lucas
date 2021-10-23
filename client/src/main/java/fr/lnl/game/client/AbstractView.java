package fr.lnl.game.client;

import fr.lnl.game.server.games.Game;

public abstract class AbstractView implements View {

    Game game;

    public AbstractView(Game game) {
        this.game = game;
    }
}
