package fr.lnl.game.server.games.grid;

import fr.lnl.game.server.games.grid.elements.*;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Pair;
import fr.lnl.game.server.utils.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class managing the board
 */
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

    /**
     * @see Grid#boardPositionIsValid(int, int)
     */
    public boolean boardPositionIsValid(int row, int deltaRow, int column, int deltaColumn){
        return boardPositionIsValid(row + deltaRow, column + deltaColumn);
    }

    /**
     * Check if given position is in grid area
     * @return true if position is valid, false otehrwise
     */
    public boolean boardPositionIsValid(int row, int column) {
        return row >= 0 && column >= 0 && row < this.row && column < this.column;
    }

    /**
     * @see Grid#boardPositionIsValid(int, int)
     */
    public boolean boardPositionIsValid(Point point) {
        return boardPositionIsValid(point.getA(), point.getB());
    }

    /**
     * @return the number of neutral box
     * @see Grid#isNeutralBox(Box)
     */
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

    /**
     *
     * @param box the box we'll look state
     * @return true if {@code box} isn't a {@link Wall} or a {@link EnergyBall}
     */
    public boolean isNeutralBox(Box box){
        return !(box instanceof Wall) && !(box instanceof EnergyBall);
    }

    /**
     * Given a string representation of the board.<br>
     * Some characters in given string are in UTF-8 and can be poorly displayed if using an incompatible encoding, like
     * on Windows where french regional encoding is {@code windows-1252}
     * @return a string view of a board
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < row; i++) {
            str.append("\n");
            for (int j = 0; j < column; j++) {
                Pair<Player, Box> value = board.get(new Point(i, j));
                if(value.getA() != null){
                    str.append(" \033[0;34m").append(value.getA().getId()).append("\033[0m");
                }
                else if (value.getB() instanceof Wall) {
                    str.append(" \033[0;32m#\033[0m");
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

    public String privateView(Player player) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < row; i++) {
            str.append("\n");
            for (int j = 0; j < column; j++) {
                Pair<Player, Box> value = board.get(new Point(i, j));
                if(value.getA() != null){
                    str.append(" \033[0;34m").append(value.getA().getId()).append("\033[0m");
                }
                else if (value.getB() instanceof Wall) {
                    str.append(" \033[0;32m#\033[0m");
                }
                else if(value.getB() instanceof EnergyBall){
                    str.append(" \033[0;31mE\033[0m");
                }
                else if(value.getB() instanceof Explosive){
                    if(((Explosive) value.getB()).getPlayer().equals(player)){
                        if(value.getB() instanceof Mine){
                            str.append(" \033[0;35mM\033[0m");
                        }
                        else if(value.getB() instanceof Bomb){
                            str.append(" \033[0;36mB\033[0m");
                        }
                    }
                    else{
                        str.append(" \033[0;37m.\033[0m");
                    }
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

    public Player getGridPlayer(Point point){
        return getBoard().get(point).getA();
    }

    public Box getGridBox(Point point){
        return getBoard().get(point).getB();
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

    public List<CountdownBox> getAllCountdownElements() {
        List<CountdownBox> list = new ArrayList<>();
        for(Pair<Player, Box> element : getBoard().values()) {
            if(element.getB() instanceof CountdownBox box) {
                list.add(box);
            }
        }
        return list;
    }
}
