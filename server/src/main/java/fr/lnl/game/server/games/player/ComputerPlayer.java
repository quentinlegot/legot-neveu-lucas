package fr.lnl.game.server.games.player;

public class ComputerPlayer extends AbstractPlayer{

    public ComputerPlayer(int id, boolean shieldDeploy, ClassPlayer classPlayer) {
        super(id, shieldDeploy, classPlayer);
    }

    public ComputerPlayer(int id, ClassPlayer classPlayer) {
        super(id,false, classPlayer);
    }
}
