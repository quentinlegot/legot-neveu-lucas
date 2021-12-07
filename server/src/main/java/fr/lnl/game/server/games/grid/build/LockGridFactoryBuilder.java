package fr.lnl.game.server.games.grid.build;

import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.grid.elements.AbstractBox;
import fr.lnl.game.server.games.grid.elements.Box;
import fr.lnl.game.server.games.grid.elements.EnergyBall;
import fr.lnl.game.server.games.grid.elements.Wall;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Pair;
import fr.lnl.game.server.utils.Point;

import java.util.Random;

public class LockGridFactoryBuilder extends AbstractGridFactoryBuilder {

    protected LockGridFactoryBuilder() {

    }

    public static GridFactoryBuilder create() {
        return new LockGridFactoryBuilder();
    }

    @Override
    protected void initGrid() {
        for (int i = 0; i < getGrid().getRow(); i++) {
            for (int j = 0; j < getGrid().getColumn(); j++) {
                Box box;
                if (i == 0 && j == 0) {
                    box = new Wall(i, j);
                } else if (i == 0 && j == getGrid().getColumn()-1) {
                    box = new Wall(i, j);
                } else if (i == getGrid().getRow()-1 && j == 0) {
                    box = new Wall(i, j);
                } else if (i == getGrid().getRow()-1 && j == getGrid().getColumn()-1) {
                    box = new Wall(i, j);
                } else if (i == 0) {
                    box = new Wall(i, j);
                } else if (i == getGrid().getRow()-1) {
                    box = new Wall(i, j);
                } else if (j == 0) {
                    box = new Wall(i, j);
                } else if (j == getGrid().getColumn()-1) {
                    box = new Wall(i, j);
                } else {
                    box = null;
                }
                getGrid().getBoard().put(new Point(i,j), new Pair<>(null,box));
            }
        }
    }

    @Override
    protected void initPlaceEnergyBall(float probability) {
        for (int i = 1; i < getGrid().getRow() - 1; i++) {
            for (int j = 1; j < getGrid().getColumn() - 1; j++) {
                if(Math.random() >= probability){
                    Point point = new Point(i,j);
                    if(!(getGrid().getBoard().get(point).getB() instanceof Wall)){
                        getGrid().getBoard().get(point).setB(new EnergyBall());
                    }
                }
            }
        }
    }

    @Override
    protected void initPlaceInternWall(float probability) {
        for (int i = 1; i < getGrid().getRow() - 1; i++) {
            for (int j = 1; j < getGrid().getColumn() - 1; j++) {
                if(Math.random() >= probability){
                    Point point = new Point(i,j);
                    if(getIllusionNumberWallNeighbour(point) <= 3){
                        getGrid().getBoard().get(point).setB(new Wall(i,j));
                    }
                    else{
                        getGrid().getBoard().get(point).setB(new AbstractBox());
                        getGrid().getBoard().get(point).getB().setLock(true);
                    }
                }
            }
        }
    }

    @Override
    public void initPlacePlayers() {
        Random random = new Random();
        Box boxTargeted;
        Player playerTargeted;
        Point point;
        for (Player player: getGrid().getPlayers()) {
            do{
                int i = random.nextInt(1,getGrid().getRow() - 1);
                int j = random.nextInt(1,getGrid().getColumn() - 1);
                point = new Point(i,j);
                Pair<Player,Box> pairTargeted = getGrid().getBoard().get(point);
                boxTargeted = pairTargeted.getB();
                playerTargeted = pairTargeted.getA();
            }while(playerTargeted != null || !getGrid().isNeutralBox(boxTargeted));
            getGrid().getBoard().get(point).setA(player);
            player.setPosition(point);
        }
    }

    public int getIllusionNumberWallNeighbour(Point point){
        int countWall = 0;
        for (int deltaRow = -1; deltaRow <= 1; deltaRow++){
            for (int deltaColomn = -1; deltaColomn <= 1; deltaColomn++) {
                Point neighbour = new Point(point.getA() + deltaRow, point.getB() + deltaColomn);
                if (getGrid().boardPositionIsValid(neighbour)) {
                    Box box = getGrid().getBoard().get(neighbour).getB();
                    if (box != null) {
                        if (box instanceof Wall || box.isLock()) {
                            countWall++;
                        }
                    }
                }
            }
        }
        return countWall;
    }
}
