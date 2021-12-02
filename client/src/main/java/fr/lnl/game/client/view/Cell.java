package fr.lnl.game.client.view;

import fr.lnl.game.server.games.grid.Bomb;
import fr.lnl.game.server.games.grid.EnergyBall;
import fr.lnl.game.server.games.grid.Mine;
import fr.lnl.game.server.games.grid.Wall;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Cardinal;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



/*classe qui a pour but de générer chaque case de la grid et de vérifier les entités présentes dessus
(Mur,Joueur,Energie,bombe,etc..)
*/

public class Cell extends Rectangle {


    public Cell(int x, int y){
        setWidth(GUI.cellSize);
        setHeight(GUI.cellSize);
        relocate(x*GUI.cellSize,y*GUI.cellSize);
        setFill(Color.valueOf("#ffffff"));
        setStroke(Color.DARKGRAY);
    }



    public static StackPane setImageObject(Object object){
        Image image;
        StackPane sp = new StackPane();

        if(object instanceof Player){
            image = new Image("file:resources/images/player.png");
        }
        else if(object instanceof EnergyBall){
            image = new Image("file:resources/images/energyBall.png");
        }
        else if(object instanceof Bomb){
            image = new Image("file:resources/images/bomb.jpg");
        }
        else if(object instanceof Mine){
            image = new Image("file:resources/images/mine.webp");
        }
        else{
            //test
            if(((Wall)object).getCardinal()== Cardinal.NORTH){
                image = new Image("file:resources/topWall.png");
            }else if(((Wall)object).getCardinal()== Cardinal.SOUTH) {
                image = new Image("file:resources/bottomWall.png");
            }else if(((Wall)object).getCardinal()== Cardinal.EAST) {
                image = new Image("file:resources/rightWall.png");
            }else if(((Wall)object).getCardinal()== Cardinal.WEST) {
                image = new Image("file:resources/leftWall.png");
            }else if(((Wall)object).getCardinal()== Cardinal.NORTH_EAST) {
                image = new Image("file:resources/topRightWall.png");
            }else if(((Wall)object).getCardinal()== Cardinal.NORTH_WEST) {
                image = new Image("file:resources/topLeftWall.png");
            }else if(((Wall)object).getCardinal()== Cardinal.SOUTH_EAST) {
                image = new Image("file:resources/bottomRightWall.png");
            }else{
                image = new Image("file:resources/bottomLeftWall.png");
            }
        }

        ImageView iv = new ImageView(image);
        iv.setFitHeight(40);
        iv.setFitWidth(40);
        sp.getChildren().add(iv);
        return sp;
    }

}
