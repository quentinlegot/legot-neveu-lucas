package fr.lnl.game.server.games.grid;

public class EnergyBall implements Box{

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return true; // no var to test
    }
}
