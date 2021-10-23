package fr.lnl.game.server.games.grid;

import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Cardinal;
import fr.lnl.game.server.utils.Pair;
import fr.lnl.game.server.utils.Point;

import java.util.HashMap;
import java.util.List;


public class Grid {
    private HashMap<Point, Pair<Player, Box>> board;
    private int row;
    private int column;
    private List<Player> players;

    public Grid(int row, int column, List<Player> players) {
        this.row = row;
        this.column = column;
        this.players = players;
        board = new HashMap<>();
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

    public static boolean caseisValid(int row, int column, int deltaRow, int deltaColumn){
        return row + deltaRow >= 0 && row + deltaRow <= row && column + deltaColumn >= 0 && column + deltaColumn <= column;
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
                    str.append(" \033[0;31mO\033[0m");
                }
                else if(value.getB() instanceof Mine){
                    str.append(" \033[0;31mX\033[0m");
                }
                else if(value.getB() instanceof Bomb){
                    str.append(" \033[0;31mI\033[0m");
                }
                else {
                    str.append(" \033[0;31m.\033[0m");
                }
            }
        }
        return str.toString();
    }

    /**
     * @deprecated modèle mvc non respecté
     */
    @Deprecated
    public void printGrid() {
        System.out.println(this);
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
