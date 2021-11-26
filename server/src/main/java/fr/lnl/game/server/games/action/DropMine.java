package fr.lnl.game.server.games.action;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.grid.Mine;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Point;

import java.util.List;

public class DropMine extends DropObject {

    public DropMine(Game game, Player player, Direction direction) throws NotValidDirectionException {
        super(game, player, direction);

    }

    /**
     * @deprecated même principe que {@link Shot#doAction()}
     */
    @Deprecated
    @Override
    public void doAction() {
        game.getGrid().getBoard().get(point).setB(new Mine());
        game.getCurrentPlayer().decrementEnergy(player.getClassPlayer().getMineCost());
    }

}
