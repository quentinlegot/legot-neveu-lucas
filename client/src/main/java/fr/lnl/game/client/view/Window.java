package fr.lnl.game.client.view;

import fr.lnl.game.client.listener.ButtonListener;
import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.grid.elements.*;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Pair;
import fr.lnl.game.server.utils.Point;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Window extends AbstractView {

    public static final int cellSize = 40;
    public static final int width = 24;
    public static final int height = 16;

    private final Stage stage;


    public Window(Stage stage, Game game, Player player) {
        super(game, player);
        this.stage = stage;
    }

    public void show() {
        // stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(createContent());
        stage.setScene(scene);
        stage.setTitle("Game");
        stage.setResizable(false);
        stage.sizeToScene();
        stage.show();
    }

    private Parent createContent() {
        Pane principalPane = new Pane();
        principalPane.setPrefSize(width * cellSize, height * cellSize); // TODO: 04/12/2021 A corriger -> doit plutôt s'adapter à la taille de la grid (grid.getRow() et grid.getColumn())

        //à définir avec n pour moduler la taille du plateau
        for (int i = 0; i < game.getGrid().getRow(); i++) {
            for (int j = 0; j < game.getGrid().getColumn(); j++) {
                Cell cell = new Cell(i, j);
                principalPane.getChildren().add(cell);
            }
        }

        Grid grid = game.getGrid();
        for (int i = 0; i < grid.getRow(); i++) {
            for (int j = 0; j < grid.getColumn(); j++) {
                Pair<Player, Box> value = grid.getBoard().get(new Point(i, j));
                if (value.getA() != null) {
                    addToPrincipalPanel(value.getA(), principalPane, i, j);
                }
                if (value.getB() instanceof Wall || value.getB() instanceof EnergyBall || value.getB() instanceof Mine || value.getB() instanceof Bomb) {
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
        followingButton.setOnAction(new ClientEventHandler(new ButtonListener(game)));
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
}
