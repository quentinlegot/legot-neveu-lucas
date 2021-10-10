package fr.lnl.game.server.games.weapon;

public class Firearm implements Weapon{
    @Override
    public int getBullet() {
        return 0;
    }

    @Override
    public int horizontalDistance() {
        return 0;
    }

    @Override
    public int verticalDistance() {
        return 0;
    }
}
