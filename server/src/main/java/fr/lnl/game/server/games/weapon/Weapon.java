package fr.lnl.game.server.games.weapon;

public interface Weapon {

    int getBullet();

    /**
     * @return distance a bullet can go horizontally
     */
    int getHorizontalDistance();

    /**
     * @return distance a bullet can go vertically
     */
    int getVerticalDistance();

}
