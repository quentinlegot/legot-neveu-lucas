package fr.lnl.game.server.games;

import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.player.Player;

public class Game {

    Grid grid;
    Player player_One;
    Player player_Two;
    Player current_player;
    Player[] players;

    public Game(Grid grid, Player player_One, Player player_Two){
        this.player_One = player_One;
        this.player_Two = player_Two;
        this.current_player = player_One;
        this.grid = grid;
        players = new Player[]{player_One, player_Two};
    }

    public void play(){

    }

    public boolean isOver(){
        return players.length == 1;
    }

    public Player getWinner(){
        // Quentin: simple avis: appel de isOver pas forcément nécessaire, puisqu'on appelera surement getWinner après
        // un appel a isOver retournant true
        if(isOver()){
            return players[0];
        }
        return null;
    }

    public Player getCurrentPlayer() {
        return current_player;
    }

    public Grid getGrid() {
        return grid;
    }

    public Player[] getPlayers() {
        return players;
    }
}