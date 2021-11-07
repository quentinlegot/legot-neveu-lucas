package fr.lnl.game.client.view;

import fr.lnl.game.server.games.grid.Bomb;
import fr.lnl.game.server.games.grid.EnergyBall;
import fr.lnl.game.server.games.grid.Mine;
import fr.lnl.game.server.games.grid.Wall;
import fr.lnl.game.server.games.player.Player;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



/*classe qui a pour but de générer chaque case de la grid et de vérifier les entités présentes dessus
(Mur,Joueur,Energie,bombe,etc..)
*/

public class Cell extends Rectangle {

    //NON-ETABLIE
    public Cell(int x, int y){
        setWidth(GUI.cellSize);
        setHeight(GUI.cellSize);
        relocate(x,y);
        setFill(Color.valueOf("#ffffff"));
        setStroke(Color.DARKGRAY);
    }


    //NON-TEST
    public static StackPane setImageObject(Object object){
        //voir pour rajouter un répertoire ou stocker les images;
        Image image;
        StackPane sp = new StackPane();
        //remplacer après par le switch dès que on aura implémenter les interfaces

        if(object instanceof Player){
            //image = new Image();
        }
        if(object instanceof EnergyBall){
            //image = new Image();
        }
        if(object instanceof Bomb){
            //image = new Image();
        }
        if(object instanceof Mine){
            //image = new Image();
        }
        if(object instanceof Wall){
            //image = new Image();
        }

        /*
        ImageView iv = new ImageView(image);
        iv.setFitHeight(40);
        iv.setFitWidth(40);
        sp.getChilldren().add(iv);
        */
        return sp;
    }

}
