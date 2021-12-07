package fr.lnl.game.client.view;

import fr.lnl.game.client.listener.ButtonListener;
import fr.lnl.game.client.listener.ClientEventHandler;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
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
        putStatePlayerPane(principalPane);
        StackPane stateMoveTextPane = showMoveText();
        stateMoveTextPane.setLayoutY(480);
        principalPane.getChildren().add(stateMoveTextPane);


        Button followingButton = new Button("SUIVANT");
        followingButton.setOnAction(new ClientEventHandler(new ButtonListener(game)));
        followingButton.setLayoutX(700);
        followingButton.setLayoutY(600);
        followingButton.setStyle("-fx-background-color: #a96806;");
        followingButton.setTextFill(javafx.scene.paint.Color.WHITE);
        principalPane.getChildren().add(followingButton);
        return principalPane;
    }

    public void addToPrincipalPanel(Object object, Pane principalPane, int i, int j) {
        StackPane sp = Cell.setImageObject(object);
        sp.setLayoutY(i * cellSize);
        sp.setLayoutX(j * cellSize);
        principalPane.getChildren().add(sp);
    }

    // TODO: 07/12/2021 WARNING : générer autant de frames qu’il y a de joueurs,(à implémenter)
    // TODO: 07/12/2021 Maintenant régler : Factorisation du code, Responsive

    //à voir si on peut faire plus proprement les deux méthodes en dessous avec une List<StackPane> ?
    public void putStatePlayerPane(Pane principalPane){
        int Y = 0;
        for(int i=0;i < game.getPlayers().size();i++){
            StackPane sp = showStatePlayer(i);
            sp.setLayoutX(480);
            sp.setLayoutY(Y);
            Y+=90;
            principalPane.getChildren().add(sp);
        }
    }

    public StackPane showStatePlayer(int playerNumber){
        StackPane subSp = new StackPane();
        String s = "Joueur " + (playerNumber+1) + "\n" +
                "Energie : " + game.getPlayers().get(playerNumber).getEnergy() + "\n" +
                "Arme : " + game.getPlayers().get(playerNumber).getWeapon().getClass().getSimpleName() + "\n";
        Text t = new Text(s);
        Rectangle r = new Rectangle();
        r.setWidth(500);
        r.setHeight(90);
        //à voir
        if(game.getPlayers().get(playerNumber).getEnergy() <= 0){
            r.setFill(Color.RED);
        }else{
            r.setFill(Color.GREEN);
        }
        r.setStrokeWidth(2);
        r.setStroke(Color.BLACK);
        subSp.getChildren().addAll(r,t);
        return subSp;
    }

    //idem que au dessus
    public StackPane showMoveText(){
        StackPane subSp = new StackPane();
        String s = "Joueur : " + (player.getId()+1) + "\n" +
                "Vient de jouer : " + game.getSelectedAction() + "\n";
        Text t = new Text(s);
        Rectangle r = new Rectangle();
        r.setWidth(478);
        r.setHeight(165);
        r.setStrokeWidth(2);
        r.setStroke(Color.BLACK);
        r.setFill(Color.WHITE);
        subSp.getChildren().addAll(r,t);
        return subSp;
    }




}
