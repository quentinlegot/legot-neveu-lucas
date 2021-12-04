package fr.lnl.game.client.view;

import fr.lnl.game.server.games.grid.elements.Bomb;
import fr.lnl.game.server.games.grid.elements.EnergyBall;
import fr.lnl.game.server.games.grid.elements.Mine;
import fr.lnl.game.server.games.player.Player;
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
        setWidth(Window.cellSize);
        setHeight(Window.cellSize);
        relocate(x*Window.cellSize,y*Window.cellSize);
        setFill(Color.valueOf("#ffffff"));
        setStroke(Color.DARKGRAY);
    }



    public static StackPane setImageObject(Object object){
        StackPane sp = new StackPane();
        String in;
        if(object instanceof Player){
            in = "player.png";
        } else if(object instanceof EnergyBall){
            in = "energyBall.png";
        } else if(object instanceof Bomb){
            in = "bomb.jpg";
        } else if(object instanceof Mine){
            in = "mine.webp";
        } else{
            in = "wall.jpg";
        }

        ImageView iv = new ImageView(new Image(in));
        iv.setFitHeight(40);
        iv.setFitWidth(40);
        sp.getChildren().add(iv);
        return sp;
    }

}
