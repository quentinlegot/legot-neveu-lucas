package fr.lnl.game.server.games.player;

import fr.lnl.game.server.games.weapon.Weapon;
import fr.lnl.game.server.utils.Point;

public class HumanPlayer extends AbstractPlayer implements Player{
    public HumanPlayer(int id, Point position, int energy, Weapon weapon, boolean shieldDeploy) {
        super(id, position, energy, weapon, shieldDeploy);
    }
}
