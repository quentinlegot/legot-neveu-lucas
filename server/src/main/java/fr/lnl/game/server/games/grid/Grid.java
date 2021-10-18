package fr.lnl.game.server.games.grid;

import fr.lnl.game.server.utils.Cardinal;
import fr.lnl.game.server.utils.Point;
import fr.lnl.game.server.utils.Triplet;

import java.util.HashMap;


public class Grid {
    private HashMap<Point, Triplet<Box, Box, Box>> board;
    private int x;
    private int y;

    public Grid(int x, int y){
        this.x = x;
        this.y = y;
        board = new HashMap<>();
    }

    public void initGrid(){
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Box box;
                if (i == 0 && j == 0) {
                    box = new Wall(Cardinal.NORTH_WEST, i, j);
                } else if (i == 0 && j == y-1) {
                    box = new Wall(Cardinal.NORTH_EAST, i, j);
                } else if (i == x-1 && j == 0) {
                    box = new Wall(Cardinal.SOUTH_WEST, i, j);
                } else if (i == x-1 && j == y-1) {
                    box = new Wall(Cardinal.SOUTH_EAST, i, j);
                } else if (i == 0) {
                    box = new Wall(Cardinal.NORTH, i, j);
                } else if (i == x-1) {
                    box = new Wall(Cardinal.SOUTH, i, j);
                } else if (j == 0) {
                    box = new Wall(Cardinal.WEST, i, j);
                } else if (j == y-1) {
                    box = new Wall(Cardinal.EAST, i, j);
                } else {
                    box = null;
                }
                board.put(new Point(i,j), new Triplet<>(box, null, null));
            }
        }
    }


    public void printGrid() {
        for (int i = 0; i < x; i++) {
            System.out.print("\n");
            for (int j = 0; j < y; j++) {
                Triplet<Box, Box, Box> value = board.get(new Point(i, j));
                if (value.getA() instanceof Wall) {
                    if (((Wall) value.getA()).getCardinal() == Cardinal.NORTH) {
                        System.out.print(" \033[0;34m—\033[0m");
                    } else if (((Wall) value.getA()).getCardinal() == Cardinal.SOUTH) {
                        System.out.print(" \033[0;31m—\033[0m");
                    } else if (((Wall) value.getA()).getCardinal() == Cardinal.WEST) {
                        System.out.print(" \033[0;33m|\033[0m");
                    } else if (((Wall) value.getA()).getCardinal() == Cardinal.EAST) {
                        System.out.print(" \033[0;32m|\033[0m");
                    } else if (((Wall) value.getA()).getCardinal() == Cardinal.NORTH_EAST) {
                        System.out.print(" \033[0;32mN\033[0m");
                    } else if (((Wall) value.getA()).getCardinal() == Cardinal.NORTH_WEST) {
                        System.out.print(" \033[0;33mN\033[0m");
                    } else if (((Wall) value.getA()).getCardinal() == Cardinal.SOUTH_EAST) {
                        System.out.print(" \033[0;32mS\033[0m");
                    } else if (((Wall) value.getA()).getCardinal() == Cardinal.SOUTH_WEST) {
                        System.out.print(" \033[0;33mS\033[0m");
                    }
                }
                else {
                    System.out.print(" \033[0;31mO\033[0m");
                }
            }
        }
    }

    public static void main(String[] args) {
        Grid grid = new Grid(10,10);
        grid.initGrid();
        grid.printGrid();
    }
}
