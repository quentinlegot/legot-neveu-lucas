package fr.lnl.game.server.games;

import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.player.ComputerPlayer;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.games.weapon.Firearm;
import fr.lnl.game.server.utils.Point;

public class Game {

    Grid grid;
    Player player_One;
    Player player_Two;
    Player current_player;
    Player[] players = {player_One,player_Two};

    public Game(Grid grid, Player player_One, Player player_Two){
        this.player_One = player_One;
        this.player_Two = player_Two;
        this.current_player = player_One;
    }


    public void play(){

    }

    public boolean isOver(){
        return players.length == 1;
    }

    public Player getWinner(){
        if(isOver()){
            return players[0];
        }
        return null;
    }

    public Player getCurrent_player() {
        return current_player;
    }
}