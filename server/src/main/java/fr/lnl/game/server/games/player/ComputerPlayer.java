package fr.lnl.game.server.games.player;

import fr.lnl.game.server.games.weapon.Weapon;
import fr.lnl.game.server.utils.Point;

public class ComputerPlayer extends AbstractPlayer implements Player{
    public ComputerPlayer(int id, Point position, int energy, Weapon weapon, boolean shieldDeploy) {
        super(id, position, energy, weapon, shieldDeploy);
    }
}
