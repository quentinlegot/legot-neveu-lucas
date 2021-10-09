package fr.unicaen.games.player;

import fr.unicaen.games.Weapon.Weapon;
import fr.unicaen.utils.Point;

public class ComputerPlayer extends AbstractPlayer{
    public ComputerPlayer(int id, Point position, int ernergy, Weapon weapon, boolean shieldDeploy) {
        super(id, position, ernergy, weapon, shieldDeploy);
    }
}
