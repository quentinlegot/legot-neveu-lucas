package fr.lnl.game.server.games.action;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.grid.Bomb;
import fr.lnl.game.server.games.player.Player;

public class DropBomb extends DropObject {

    public DropBomb(Game game, Player player, Direction direction) throws NotValidDirectionException {
        super(game, player, direction);
    }

    @Override
    public void doAction() {
        game.getGrid().getBoard().get(point).setB(new Bomb());
        player.decrementEnergy(player.getClassPlayer().getBombCost());
    }

    @Override
    public boolean isPossible() {
        return super.isPossible();
    }

}

