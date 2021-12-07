package fr.lnl.game.client.view;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.player.Player;

public class Terminal extends AbstractView {

    public Terminal(Game game, Player player) {
        super(game, player);
    }

    public void show() {
        System.out.println(game.getGrid().toString());
    }

    @Override
    public void displayWinner(Player winner) {
        System.out.println("Le joueur " + winner + " a gagné la partie");
    }

}
