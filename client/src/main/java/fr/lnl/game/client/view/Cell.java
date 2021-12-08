package fr.lnl.game.client.view;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.action.Shot;
import fr.lnl.game.server.games.grid.elements.Bomb;
import fr.lnl.game.server.games.grid.elements.EnergyBall;
import fr.lnl.game.server.games.grid.elements.Mine;
import fr.lnl.game.server.games.player.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * classe qui a pour but de générer chaque case de la grid et de vérifier les entités présentes dessus
 * (Mur, Joueur, Energie, Bombe, etc.)
 */
public class Cell extends Rectangle {

    private static final Image PLAYER_IMAGE = new Image("player.png");
    private static final Image PLAYER_SHIELD_IMAGE = new Image("player_shield.png");
    private static final Image PLAYER_SHOT_IMAGE = new Image("player_shot.png");
    private static final Image ENERGY_BALL_IMAGE = new Image("energyBall.png");
    private static final Image BOMB_IMAGE = new Image("bomb.png");
    private static final Image MINE_IMAGE = new Image("mine.png");
    private static final Image WALL_IMAGE = new Image("wall.jpg");

    public Cell(int x, int y){
        setWidth(Window.cellSize);
        setHeight(Window.cellSize);
        relocate(x*Window.cellSize,y*Window.cellSize);
        setFill(Color.valueOf("#ffffff"));
        setStroke(Color.DARKGRAY);
    }



    public static StackPane setImageObject(Object object, Game game){
        StackPane sp = new StackPane();
        Image in;
        if(object instanceof Player){
            if(object.equals(game.getCurrentPlayer()) && game.getSelectedAction() instanceof Shot){
                in = PLAYER_SHOT_IMAGE;
            }
            else if(((Player) object).isShieldDeploy()){
                in = PLAYER_SHIELD_IMAGE;
            }
            else{
                in = PLAYER_IMAGE;
            }
        } else if(object instanceof EnergyBall){
            in = ENERGY_BALL_IMAGE;
        } else if(object instanceof Bomb){
            in = BOMB_IMAGE;
        } else if(object instanceof Mine){
            in = MINE_IMAGE;
        } else{
            in = WALL_IMAGE;
        }

        ImageView iv = new ImageView(in);
        iv.setFitHeight(Window.cellSize);
        iv.setFitWidth(Window.cellSize);
        sp.getChildren().add(iv);
        return sp;
    }

}
