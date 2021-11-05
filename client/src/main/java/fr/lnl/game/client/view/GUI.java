package fr.lnl.game.client.view;


import fr.lnl.game.server.games.grid.Box;
import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Pair;
import fr.lnl.game.server.utils.Point;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.HashMap;

public class GUI {

    //à revoir pour respecter MVC
    HashMap<Point, Pair<Player, Box>> board;
    Stage stage;
    Scene scene;
    Grid grid;
    String text ="";


    //temporaire
    public static final int cellSize = 40;
    public static final int width = 24;
    public static final int height = 16;

    public GUI(){
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        //ce n'est pas à la vue de gérer ça donc à voir
        //grid = getGrid();
        scene = new Scene(createContent());
        stage.setScene(scene);
        stage.setTitle("Game");
        stage.setResizable(false);
        stage.show();
    }

    private Parent createContent() {
        Pane principalPane = new Pane();


        return principalPane;
    }


}
