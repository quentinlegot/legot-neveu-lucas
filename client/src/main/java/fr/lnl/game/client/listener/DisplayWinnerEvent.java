package fr.lnl.game.client.listener;

import fr.lnl.game.client.App;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.listener.AbstractModelListening;

public class DisplayWinnerEvent extends AbstractModelListening {

    @Override
    public void updateModel(Object obj) {
        Player winner = (Player) obj;
        App.getViewManager().displayWinner(winner);
    }
}
