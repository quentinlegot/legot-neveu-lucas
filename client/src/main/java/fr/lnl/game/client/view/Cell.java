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
            image = new Image("/images/player.png");
        }
        else if(object instanceof EnergyBall){
            image = new Image("/images/energyBall.png");
        }
        else if(object instanceof Bomb){
            image = new Image("/images/bomb.jpg");
        }
        else if(object instanceof Mine){
            image = new Image("/images/mine.webp");
        }
        else{
            //test
            if(((Wall)object).getCardinal()== Cardinal.NORTH){
                image = new Image("/images/topWall.png");
            }else if(((Wall)object).getCardinal()== Cardinal.SOUTH) {
                image = new Image("/images/bottomWall.png");
            }else if(((Wall)object).getCardinal()== Cardinal.EAST) {
                image = new Image("/images/rightWall.png");
            }else if(((Wall)object).getCardinal()== Cardinal.WEST) {
                image = new Image("/images/rightWall.png"); // TODO: 03/12/2021 a replace par leftWall
            }else if(((Wall)object).getCardinal()== Cardinal.NORTH_EAST) {
                image = new Image("/images/topRightWall.png");
            }else if(((Wall)object).getCardinal()== Cardinal.NORTH_WEST) {
                image = new Image("/images/topLeftWall.png");
            }else if(((Wall)object).getCardinal()== Cardinal.SOUTH_EAST) {
                image = new Image("/images/bottomRightWall.png");
            }else{
                image = new Image("/images/bottomLeftWall.png");
            }
        }

        ImageView iv = new ImageView(image);
        iv.setFitHeight(40);
        iv.setFitWidth(40);
        sp.getChildren().add(iv);
        return sp;
    }

}
