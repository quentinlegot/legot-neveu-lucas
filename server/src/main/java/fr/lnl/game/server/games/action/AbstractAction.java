package fr.lnl.game.server.games.action;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.player.Player;

public abstract class AbstractAction implements Action {

    protected final Game game;
    protected final Player player;

    public AbstractAction(Game game, Player player){
        this.game = game;
        this.player = player;
    }

}
