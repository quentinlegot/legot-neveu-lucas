package fr.lnl.game.server.games.weapon;

public class Firearm implements Weapon{

    int bullet;
    int horizontalDistance;
    int verticalDistance;

    public Firearm(){
        this.bullet = 10;
        this.horizontalDistance = 3;
        this.verticalDistance = 3;
    }


    @Override
    public int getBullet() {
        return this.bullet;
    }

    @Override
    public int getHorizontalDistance() {
        return this.horizontalDistance;
    }

    @Override
    public int getVerticalDistance() {
        return this.verticalDistance;
    }
}
