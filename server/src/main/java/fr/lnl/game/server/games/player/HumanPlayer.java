package fr.lnl.game.server.games.player;


public class HumanPlayer extends AbstractPlayer {

    public HumanPlayer(int id, boolean shieldDeploy, ClassPlayer classPlayer) {
        super(id, shieldDeploy, classPlayer);
    }

    public HumanPlayer(int id, ClassPlayer classPlayer) {
        super(id, false, classPlayer);
    }
}
