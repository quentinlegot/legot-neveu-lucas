package fr.lnl.game.server.games.weapon;

public class Firearm implements Weapon{

    int bullet;
    int horizontalDistance;
    int verticalDistance;

    public Firearm(){
        this.bullet = 10;
        this.horizontalDistance = 10;
        this.verticalDistance = 10;
    }


    @Override
    public int getBullet() {
        return 0;
    }

    @Override
    public int getHorizontalDistance() {
        return 0;
    }

    @Override
    public int getVerticalDistance() {
        return 0;
    }
}
