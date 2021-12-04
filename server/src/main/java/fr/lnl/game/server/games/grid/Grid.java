package fr.lnl.game.server.games.grid;
import fr.lnl.game.server.games.grid.elements.*;
import fr.lnl.game.server.games.player.Player;
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

    public int getNumberNeutralBox(){
        int countBox = 0;
        for (int i = 1; i < row - 1; i++) {
            for (int j = 1; j < column - 1; j++) {
                Box box = board.get(new Point(i,j)).getB();
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
                    str.append(" \033[0;32m□\033[0m");
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

    public HashMap<Point, Pair<Player, Box>> getBoard() {
        return board;
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
