package fr.lnl.game.server.games.grid;

import fr.lnl.game.server.games.player.AbstractPlayer;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Cardinal;
import fr.lnl.game.server.utils.Pair;
import fr.lnl.game.server.utils.Point;

import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class Grid {
    private final HashMap<Point, Pair<Player, Box>> board;
    private final int row;
    private final int column;
    private final List<Player> players;

    public Grid(int row, int column, List<Player> players, float wallProbability, float energyProbability) {
        this.row = row;
        this.column = column;
        this.players = players;
        board = new HashMap<>();
        initBoard(wallProbability, energyProbability);
    }

    public void initBoard(float wallProbability, float energyProbability){
        initGrid();
        initPlaceInternWall(wallProbability);
        initPlaceEnergyBall(energyProbability);
    }

    public void initGrid(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                Box box;
                if (i == 0 && j == 0) {
                    box = new Wall(Cardinal.NORTH_WEST, i, j);
                } else if (i == 0 && j == column-1) {
                    box = new Wall(Cardinal.NORTH_EAST, i, j);
                } else if (i == row-1 && j == 0) {
                    box = new Wall(Cardinal.SOUTH_WEST, i, j);
                } else if (i == row-1 && j == column-1) {
                    box = new Wall(Cardinal.SOUTH_EAST, i, j);
                } else if (i == 0) {
                    box = new Wall(Cardinal.NORTH, i, j);
                } else if (i == row-1) {
                    box = new Wall(Cardinal.SOUTH, i, j);
                } else if (j == 0) {
                    box = new Wall(Cardinal.WEST, i, j);
                } else if (j == column-1) {
                    box = new Wall(Cardinal.EAST, i, j);
                } else {
                    box = null;
                }
                board.put(new Point(i,j), new Pair<>(null,box));
            }
        }
    }

    public void initPlacePlayers(){
        Random random = new Random();
        Box boxTargeted;
        Player playerTargeted;
        Point point;
        for (Player player: players) {
            do{
                int i = random.nextInt(1,getRow() - 1);
                int j = random.nextInt(1,getColumn() - 1);
                point = new Point(i,j);
                Pair<Player,Box> pairTargeted = getBoard().get(point);
                boxTargeted = pairTargeted.getB();
                playerTargeted = pairTargeted.getA();
            }while(playerTargeted != null || !isNeutralBox(boxTargeted));
            getBoard().get(point).setA(player);
            player.setPosition(point);
        }
    }


    public void initPlaceEnergyBall(float probability){
        for (int i = 1; i < row - 1; i++) {
            for (int j = 1; j < column - 1; j++) {
                if(Math.random() >= probability){
                    Point point = new Point(i,j);
                    if(!(getBoard().get(point).getB() instanceof Wall)){
                        getBoard().get(point).setB(new EnergyBall());
                    }
                }
            }
        }
    }

    public void initPlaceInternWall(float probability){
        for (int i = 1; i < row - 1; i++) {
            for (int j = 1; j < column - 1; j++) {
                if(Math.random() >= probability){
                    Point point = new Point(i,j);
                    if(getIllusionNumberWallNeighbour(point) <= 3){
                        getBoard().get(point).setB(new Wall(Cardinal.getRandom(),i,j));
                    }
                    else{
                        getBoard().get(point).setB(new AbstractBox());
                        getBoard().get(point).getB().setLock(true);
                    }
                }
            }
        }
    }

    public boolean boardPositionIsValid(int row, int deltaRow, int column, int deltaColumn){
        return boardPositionIsValid(row + deltaRow, column + deltaColumn);
    }

    public boolean boardPositionIsValid(int row, int column) {
        return row >= 0 && column >= 0 && row < this.row && column < this.column;
    }

    public boolean boardPositionIsValid(Point point) {
        return boardPositionIsValid(point.getA(), point.getB());
    }

    public int getIllusionNumberWallNeighbour(Point point){
        int countWall = 0;
        for (int deltaRow = -1; deltaRow <= 1; deltaRow++){
            for (int deltaColomn = -1; deltaColomn <= 1; deltaColomn++) {
                Point neighbour = new Point(point.getA() + deltaRow, point.getB() + deltaColomn);
                if (boardPositionIsValid(neighbour)) {
                    Box box = getBoard().get(neighbour).getB();
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

    public int getNumberNeutralBox(){
        int countBox = 0;
        for (int i = 1; i < row - 1; i++) {
            for (int j = 1; j < column - 1; j++) {
                Box box = getBoard().get(new Point(i,j)).getB();
                if(isNeutralBox(box)){
                    countBox++;
                }
            }
        }
        return countBox;
    }

    public boolean isNeutralBox(Box box){
        return !(box instanceof Wall) && !(box instanceof EnergyBall);
    }

    public HashMap<Point, Pair<Player, Box>> getBoard() {
        return board;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < row; i++) {
            str.append("\n");
            for (int j = 0; j < column; j++) {
                Pair<Player, Box> value = board.get(new Point(i, j));
                if(value.getA() != null){
                    str.append(" \033[0;34mP\033[0m");
                }
                else if (value.getB() instanceof Wall) {
                    if (((Wall) value.getB()).getCardinal() == Cardinal.NORTH) {
                        str.append(" \033[0;34m—\033[0m");
                    } else if (((Wall) value.getB()).getCardinal() == Cardinal.SOUTH) {
                        str.append(" \033[0;31m—\033[0m");
                    } else if (((Wall) value.getB()).getCardinal() == Cardinal.WEST) {
                        str.append(" \033[0;33m|\033[0m");
                    } else if (((Wall) value.getB()).getCardinal() == Cardinal.EAST) {
                        str.append(" \033[0;32m|\033[0m");
                    } else if (((Wall) value.getB()).getCardinal() == Cardinal.NORTH_EAST) {
                        str.append(" \033[0;32mN\033[0m");
                    } else if (((Wall) value.getB()).getCardinal() == Cardinal.NORTH_WEST) {
                        str.append(" \033[0;33mN\033[0m");
                    } else if (((Wall) value.getB()).getCardinal() == Cardinal.SOUTH_EAST) {
                        str.append(" \033[0;32mS\033[0m");
                    } else if (((Wall) value.getB()).getCardinal() == Cardinal.SOUTH_WEST) {
                        str.append(" \033[0;33mS\033[0m");
                    }
                }
                else if(value.getB() instanceof EnergyBall){
                    str.append(" \033[0;31mE\033[0m");
                }
                else if(value.getB() instanceof Mine){
                    str.append(" \033[0;35mM\033[0m");
                }
                else if(value.getB() instanceof Bomb){
                    str.append(" \033[0;36mB\033[0m");
                }
                else {
                    str.append(" \033[0;37m.\033[0m");
                }
            }
        }
        return str.toString();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
