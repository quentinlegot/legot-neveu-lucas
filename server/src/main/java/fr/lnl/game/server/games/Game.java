package fr.lnl.game.server.games;

import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.player.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {

    Grid grid;
    Player player_One;
    Player player_Two;
    Player current_player;
    ArrayList<Player> players;

    public Game(Grid grid, Player player_One, Player player_Two){
        this.player_One = player_One;
        this.player_Two = player_Two;
        this.current_player = player_One;
        this.grid = grid;
        players = new ArrayList<Player>(Arrays.asList(player_One, player_Two));
    }

    public boolean isOver(){
        return players.size() == 1;
    }

    public Player getWinner(){
        // Quentin: simple avis: appel de isOver pas forcément nécessaire, puisqu'on appelera surement getWinner après
        // un appel a isOver retournant true
        if(isOver()){
            return players.get(0);
        }
        return null;
    }

    public Player getCurrentPlayer() {
        return current_player;
    }

    public void setCurrent_player(Player current_player) {
        this.current_player = current_player;
    }

    public void decrementPlayers(Player player){
        players.remove(player);
    }

    public Grid getGrid() {
        return grid;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}