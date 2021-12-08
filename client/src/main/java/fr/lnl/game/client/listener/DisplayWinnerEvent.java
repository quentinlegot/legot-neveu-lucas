package fr.lnl.game.client.listener;

import fr.lnl.game.client.App;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.listener.AbstractModelListening;

public class DisplayWinnerEvent extends AbstractModelListening {

    // TODO: 07/12/2021 PROBLEM -> ViewManager retourne null
    @Override
    public void updateModel(Object obj) {
        Player winner = (Player) obj;
        App.getViewManager().displayWinner(winner);
    }
}
