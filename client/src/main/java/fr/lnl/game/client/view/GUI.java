package fr.lnl.game.client.view;


import fr.lnl.game.server.games.grid.*;
import fr.lnl.game.server.games.grid.elements.*;
import fr.lnl.game.server.games.player.ClassPlayer;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.games.player.RandomComputerPlayer;
import fr.lnl.game.server.utils.Pair;
import fr.lnl.game.server.utils.Point;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GUI {

    //à revoir pour respecter MVC
    HashMap<Point, Pair<Player, Box>> board;
    Stage stage;
    Scene scene;
    Grid grid;


    public static final int cellSize = 40;
    public static final int width = 24;
    public static final int height = 16;

    public GUI() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        //à enlever avec sa méthode car ne respecte pas mvc
        grid = getGrid();

        scene = new Scene(createContent());

        stage.setScene(scene);
        stage.setTitle("Game");
        stage.setResizable(false);
        stage.show();
    }

    private Parent createContent() {
        Pane principalPane = new Pane();
        principalPane.setPrefSize(width * cellSize, height * cellSize);

        //à définir avec n pour moduler la taille du plateau
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                Cell cell = new Cell(i, j);
                principalPane.getChildren().add(cell);
            }
        }

        board = grid.getBoard();
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                Pair<Player, Box> value = board.get(new Point(i, j));
                if (value.getA() != null) {
                    addToPrincipalPanel(value.getA(), principalPane, i, j);
                }
                if (value.getB() instanceof Wall || value.getB() instanceof EnergyBall || value.getB() instanceof Mine || value.getB() instanceof Bomb) {
                    System.out.println(value.getB());
                    addToPrincipalPanel(value.getB(), principalPane, i, j);
                }
            }
        }

        Rectangle shape = new Rectangle();
        shape.setX(700);
        shape.setY(20);
        shape.setWidth(200);
        shape.setHeight(600);
        shape.setFill(javafx.scene.paint.Color.WHITE);


        Button followingButton = new Button("SUIVANT");
        followingButton.setLayoutX(775);
        followingButton.setLayoutY(600);
        followingButton.setStyle("-fx-background-color: #a96806;");
        followingButton.setTextFill(javafx.scene.paint.Color.WHITE);
        //add un eventListener au button

        principalPane.getChildren().add(followingButton);
        //pas compris le principe
        return principalPane;
    }

    public Pane addToPrincipalPanel(Object object, Pane principalPane, int i, int j) {
        StackPane sp = Cell.setImageObject(object);
        sp.setLayoutY(i * cellSize);
        sp.setLayoutX(j * cellSize);
        principalPane.getChildren().add(sp);
        return principalPane;
    }


    //méthode à supprimer existe juste pour test
    private Grid getGrid() {
        List<Player> players = Arrays.asList(new RandomComputerPlayer(1, null, ClassPlayer.DEFAULT),
                new RandomComputerPlayer(2, null, ClassPlayer.DEFAULT));
        Grid grid =  new Grid(16, 16, players, 0.80F, 0.95F);
        //grid.placePlayersBRUT();
        return grid;
    }


}
