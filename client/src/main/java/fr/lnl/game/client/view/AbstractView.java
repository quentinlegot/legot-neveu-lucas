package fr.lnl.game.client.view;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.action.ReunionSameAction;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Maths;
import java.util.List;
import java.util.Scanner;

public abstract class AbstractView implements View {

    protected final Player player;
    protected Game game;

    public AbstractView(Game game, Player player) {
        this.game = game;
        this.player = player;
    }
}
