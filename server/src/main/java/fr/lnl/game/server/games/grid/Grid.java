package fr.lnl.game.server.games.grid;

import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Cardinal;
import fr.lnl.game.server.utils.Pair;
import fr.lnl.game.server.utils.Point;

import java.util.HashMap;


public class Grid {
    private HashMap<Point, Pair<Player, Box>> board;
    private int row;
    private int column;
    private Player[] players;

    public Grid(int row, int column, Player[] players){
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
        board.get(new Point(1,1)).setA(players[0]);
        board.get(new Point(14,14)).setA(players[1]);
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

    public void printGrid() {
        for (int i = 0; i < row; i++) {
            System.out.print("\n");
            for (int j = 0; j < column; j++) {
                Pair<Player, Box> value = board.get(new Point(i, j));
                if(value.getA() != null){
                    System.out.print(" \033[0;34mP\033[0m");
                }
                else if (value.getB() instanceof Wall) {
                    if (((Wall) value.getB()).getCardinal() == Cardinal.NORTH) {
                        System.out.print(" \033[0;34m—\033[0m");
                    } else if (((Wall) value.getB()).getCardinal() == Cardinal.SOUTH) {
                        System.out.print(" \033[0;31m—\033[0m");
                    } else if (((Wall) value.getB()).getCardinal() == Cardinal.WEST) {
                        System.out.print(" \033[0;33m|\033[0m");
                    } else if (((Wall) value.getB()).getCardinal() == Cardinal.EAST) {
                        System.out.print(" \033[0;32m|\033[0m");
                    } else if (((Wall) value.getB()).getCardinal() == Cardinal.NORTH_EAST) {
                        System.out.print(" \033[0;32mN\033[0m");
                    } else if (((Wall) value.getB()).getCardinal() == Cardinal.NORTH_WEST) {
                        System.out.print(" \033[0;33mN\033[0m");
                    } else if (((Wall) value.getB()).getCardinal() == Cardinal.SOUTH_EAST) {
                        System.out.print(" \033[0;32mS\033[0m");
                    } else if (((Wall) value.getB()).getCardinal() == Cardinal.SOUTH_WEST) {
                        System.out.print(" \033[0;33mS\033[0m");
                    }
                }
                else if(value.getB() instanceof EnergyBall){
                    System.out.print(" \033[0;31mO\033[0m");
                }
                else if(value.getB() instanceof Mine){
                    System.out.print(" \033[0;31mX\033[0m");
                }
                else if(value.getB() instanceof Bomb){
                    System.out.print(" \033[0;31mI\033[0m");
                }
                else {
                    System.out.print(" \033[0;31m.\033[0m");
                }
            }
        }
    }
}
