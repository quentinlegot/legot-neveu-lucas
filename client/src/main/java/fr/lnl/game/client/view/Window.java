package fr.lnl.game.client.view;

import fr.lnl.game.client.App;
import fr.lnl.game.client.listener.ButtonListener;
import fr.lnl.game.client.listener.ClientEventHandler;
import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.action.Action;
import fr.lnl.game.server.games.action.ReunionSameAction;
import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.grid.elements.*;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Pair;
import fr.lnl.game.server.utils.Point;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class Window extends AbstractView {


    //il faut pouvoir trouver une formule responsive avec width et height
    public static final int cellSize = 40;
    public static final int width = 500;
    public static final int height = 160;
    private static final Color dark = Color.valueOf("1F1F1F");
    private static final Background bg = new Background(new BackgroundFill(dark, CornerRadii.EMPTY, Insets.EMPTY));


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

    @Override
    public void displayWinner(Player winner) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Fin du jeu");
        alert.setHeaderText("La partie est terminé");
        alert.setContentText("Un joueur " + winner + " a gagné");
        App.getViewManager().updateView();
        alert.showAndWait();
    }

    @Override
    public Action choseAction() {
        List<ReunionSameAction> actions = player.generateAvailableActions();
        List<Action> listActions = choseReunionSameAction(actions).getActions();
        Action action = null;
        do {
            if(listActions.size() == 1){
                return listActions.get(0);
            }
            // TODO: 08/12/2021 implémenter choix voir Terminal pour savoir comment faire
        }while(action == null);
        return action;
    }

    private Parent createContent() {
        Pane principalPane = new Pane();
        principalPane.setPrefSize(game.getGrid().getRow() * cellSize + width, game.getGrid().getColumn() * cellSize + height); // TODO: 04/12/2021 A corriger -> doit plutôt s'adapter à la taille de la grid (grid.getRow() et grid.getColumn())
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
                if (value.getB() instanceof Wall || value.getB() instanceof EnergyBall) {
                    addToPrincipalPanel(value.getB(), principalPane, i, j);
                }
                else if(value.getB() instanceof Explosive){
                    if(((Explosive) value.getB()).getPlayer().equals(player)){
                        addToPrincipalPanel(value.getB(), principalPane, i, j);
                    }
                    else{
                        addToPrincipalPanel(null,principalPane, i, j);
                    }
                }
                else if (value.getA() != null) {
                    addToPrincipalPanel(value.getA(), principalPane, i, j);
                }
                else{
                    addToPrincipalPanel(null,principalPane, i, j);
                }
            }
        }
        putStatePlayerPane(principalPane);
        putMoveTextPane(principalPane);
        Button buttonNext = new Button("SUIVANT");
        buttonNext .setOnAction(new ClientEventHandler(new ButtonListener(game)));
        buttonNext .setLayoutX(700);
        buttonNext .setLayoutY(600);
        buttonNext .setStyle("-fx-background-color: #a96806;");
        buttonNext .setTextFill(javafx.scene.paint.Color.WHITE);
        principalPane.getChildren().add(buttonNext);
        principalPane.setBackground(bg);
        return principalPane;
    }

    public void addToPrincipalPanel(Object object, Pane principalPane, int i, int j) {
        StackPane sp = Cell.setImageObject(object, game);
        sp.setLayoutY(i * cellSize);
        sp.setLayoutX(j * cellSize);
        principalPane.getChildren().add(sp);
    }

    // TODO: 07/12/2021 Maintenant régler :  Responsive


    public void putStatePlayerPane(Pane principalPane){
        int Y = 0;
        for(int i=0;i<game.getPlayers().size();i++){
            StackPane sp = showStatePlayer(game.getPlayers().get(i).toString(),i);
            sp.setLayoutX(480);
            sp.setLayoutY(Y);
            Y+=90;
            principalPane.getChildren().add(sp);
        }
    }

    public StackPane showStatePlayer(String type, int playerNumber){
        StackPane subSp = new StackPane();
        String s = type + " " + (playerNumber+1) + "\n" +
                "Energie : " + game.getPlayers().get(playerNumber).getEnergy() + "\n" +
                "Arme : " + game.getPlayers().get(playerNumber).getWeapon().getClass().getSimpleName() + "\n";
        Text t = new Text(s);
        Rectangle r = new Rectangle();
        r.setWidth(500);
        r.setHeight(90);
        if(game.getPlayers().get(playerNumber).getEnergy() <= 0){
            r.setFill(Color.valueOf("A54747"));
        }else{
            r.setFill(Color.valueOf("62B262"));
        }
        r.setStrokeWidth(1);
        r.setStroke(Color.BLACK);
        subSp.getChildren().addAll(r,t);
        return subSp;
    }

    public void putMoveTextPane(Pane principalPane){
        StackPane stateMoveTextPane = showMoveText();
        stateMoveTextPane.setLayoutY(480);
        principalPane.getChildren().add((stateMoveTextPane));
    }

    public StackPane showMoveText(){
        StackPane subSp = new StackPane();
        String action = game.getSelectedAction() == null ? "null" : game.getSelectedAction().getClass().getSimpleName();
        String s = player +  " : " + (player.getId()+1) + "\n" +
                "Vient de jouer : " + action + "\n";
        Text t = new Text(s);
        t.setFill(Color.WHITE);
        Rectangle r = new Rectangle();
        r.setWidth(478);
        r.setHeight(165);
        r.setStrokeWidth(1);
        r.setStroke(Color.BLACK);
        r.setFill(dark);
        subSp.getChildren().addAll(r,t);
        return subSp;
    }




}
