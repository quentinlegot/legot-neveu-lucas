package fr.lnl.game.server.games.player;

import fr.lnl.game.server.utils.Point;

public class ComputerPlayer extends AbstractPlayer{

    public ComputerPlayer(int id, Point point, ClassPlayer classPlayer) {
        super(id,point,false, classPlayer);
    }
}
