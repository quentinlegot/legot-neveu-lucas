package fr.lnl.game.server.games.grid;

import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Cardinal;
import fr.lnl.game.server.utils.Pair;
import fr.lnl.game.server.utils.Point;

import java.util.HashMap;
import java.util.List;


public class Grid {
    private final HashMap<Point, Pair<Player, Box>> board;
    private final int row;
    private final int column;
    private final List<Player> players;

    public Grid(int row, int column, List<Player> players) {
        this.row = row;
        this.column = column;
        this.players = players;
        board = new HashMap<>();
        initGrid();
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

    public void placePlayersBRUT(){
        board.get(new Point(1,1)).setA(players.get(0));
        board.get(new Point(14,14)).setA(players.get(1));
    }

    public void placeEnergyBallBRUT(){
        board.get(new Point(2,3)).setB(new EnergyBall());
        board.get(new Point(7,10)).setB(new EnergyBall());
    }

    public void placeInternWallBRUT(){
        board.get(new Point(3,6)).setB(new Wall(Cardinal.NORTH,3,6));
        board.get(new Point(7,14)).setB(new Wall(Cardinal.SOUTH,7,14));
        board.get(new Point(10,7)).setB(new Wall(Cardinal.EAST,10,7));
        board.get(new Point(14,2)).setB(new Wall(Cardinal.WEST,14,2));
    }

    public boolean boardPositionIsValid(int row, int deltaRow, int column, int deltaColumn){
        return row + deltaRow >= 0 && row + deltaRow < this.row && column + deltaColumn >= 0 && column + deltaColumn < this.column;
    }

    public boolean boardHorizontalIsValid(int column, int deltaColumn){
        return column + deltaColumn >= 0 && column + deltaColumn < this.column;
    }

    public boolean boardVerticalIsValid(int row, int deltaRow){
        return row + deltaRow >= 0 && row + deltaRow < this.row;
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
