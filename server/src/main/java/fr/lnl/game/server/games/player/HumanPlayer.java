package fr.lnl.game.server.games.player;

import fr.lnl.game.server.utils.Point;

/**
 * Instance of Human Player.<br>
 * A human player choose an action to execute by using mouse or keyboard.<br>
 * Human Player don't implement choseAction cause this method is executed on client part
 */
public class HumanPlayer extends AbstractPlayer {

    public HumanPlayer(Integer id, Point point, ClassPlayer classPlayer) {
        super(id, point,false, classPlayer);
    }


    @Override
    public String toString() {
        return "Human";
    }
}
