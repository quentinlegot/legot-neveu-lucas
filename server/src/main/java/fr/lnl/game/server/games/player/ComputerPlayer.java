package fr.lnl.game.server.games.player;

import fr.lnl.game.server.utils.Point;

public class ComputerPlayer extends AbstractPlayer{

    public ComputerPlayer(int id, Point position, boolean shieldDeploy, ClassPlayer classPlayer) {
        super(id, position, shieldDeploy, classPlayer);
    }

    public ComputerPlayer(int id, ClassPlayer classPlayer) {
        super(id, null,false, classPlayer);
    }
}
