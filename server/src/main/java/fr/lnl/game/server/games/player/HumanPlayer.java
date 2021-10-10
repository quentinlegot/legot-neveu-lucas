package fr.lnl.game.server.games.player;

import fr.lnl.game.server.games.weapon.Weapon;
import fr.unicaen.utils.Point;

public class HumanPlayer extends AbstractPlayer{
    public HumanPlayer(int id, Point position, int ernergy, Weapon weapon, boolean shieldDeploy) {
        super(id, position, ernergy, weapon, shieldDeploy);
    }
}
