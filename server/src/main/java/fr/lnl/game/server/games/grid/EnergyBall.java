package fr.lnl.game.server.games.grid;

public class EnergyBall implements Box{

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();// no var to test
    }
}
