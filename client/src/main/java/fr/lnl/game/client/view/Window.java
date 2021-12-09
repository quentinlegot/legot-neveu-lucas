package fr.lnl.game.client.view;

import fr.lnl.game.client.App;
import fr.lnl.game.client.listener.ClientEventHandler;
import fr.lnl.game.client.listener.NextPlayerButtonListener;
import fr.lnl.game.client.listener.SelectActionButton;
import fr.lnl.game.client.listener.SelectDirectionListener;
import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.action.*;
import fr.lnl.game.server.games.grid.Grid;
import fr.lnl.game.server.games.grid.elements.Box;
import fr.lnl.game.server.games.grid.elements.EnergyBall;
import fr.lnl.game.server.games.grid.elements.Explosive;
import fr.lnl.game.server.games.grid.elements.Wall;
import fr.lnl.game.server.games.player.HumanPlayer;
import fr.lnl.game.server.games.player.Player;
import fr.lnl.game.server.utils.Pair;
import fr.lnl.game.server.utils.Point;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

/**
 * Window view, use mouse and keyboard to control interface
 */
public class Window extends AbstractView {


    // il faut pouvoir trouver une formule responsive avec width et height
    public static final int cellSize = 40;
    public static final int width = 500;
    public static final int height = 160;
    private static final Color dark = Color.valueOf("1F1F1F");
    private static final Background bg = new Background(new BackgroundFill(dark, CornerRadii.EMPTY, Insets.EMPTY));


    private final Stage stage;
    private Pane buttonPane;
    private ReunionSameAction selectedReunionAction = null;
    private final NextPlayerButtonListener nextPlayerButtonListener = new NextPlayerButtonListener(game);


    public Window(Stage stage, Game game, Player player) {
        super(game, player);
        this.stage = stage;
    }

    /**
     * used to update screen
     */
    public void show() {
        Scene scene = new Scene(createContent());
        stage.setScene(scene);
        stage.setTitle("Game");
        stage.getIcons().add(Cell.getPlayerImage());
        stage.setResizable(false);
        stage.sizeToScene();
        stage.show();
    }

    /**
     * Used to display the name of the winner
     * @param winner the player who win the game, can be Null
     */
    @Override
    public void displayWinner(Player winner) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Fin du jeu");
        alert.setHeaderText("La partie est termin\u00E9");
        if(winner == null) {
            alert.setContentText("La partie est nulle, personne n'a gagn\u00E9");
        } else {
            alert.setContentText("Le joueur " + winner + " " + winner.getId() + " a gagn\u00E9");
        }
        App.getViewManager().updateView();
        alert.showAndWait();
    }

    /**
     * Choose a direction between all possible from the previously selected action type
     * @param selectedReunionAction previously selected type of action
     * @see Window#choseReunionSameAction(List)
     */
    public void choseDirectionAction(ReunionSameAction selectedReunionAction) {
        for(int i = 0; i < selectedReunionAction.getActions().size(); ++i) {
            Action action = selectedReunionAction.getActions().get(i);
            if(action instanceof Move m){
                addButtonToPane(m.getDirection().toString(),
                        new ClientEventHandler(new SelectDirectionListener(game, this, action)), buttonPane,
                        i * 100 + 50, 0);
            }
            else if(action instanceof DropObject o){
                addButtonToPane(o.getDirection().toString(), new ClientEventHandler(new SelectDirectionListener(game, this, action)), buttonPane, i * 100 + 50, 0);
            }
            else if(action instanceof Shot s){
                addButtonToPane(s.getPoint().toString(), new ClientEventHandler(new SelectDirectionListener(game, this, action)), buttonPane, i * 100 + 50, 0);
            }
            else{
                addButtonToPane(action.getClass().getSimpleName(), new ClientEventHandler(new SelectDirectionListener(game, this, action)), buttonPane, i * 100 + 50, 0);
            }
        }
    }

    /**
     * Used when {@link Game#getCurrentPlayer()} is an instance of {@link HumanPlayer}.
     * Display button to demand to player to choose the type of action to execute
     * @param actions the list of possible actions
     * @see Window#choseDirectionAction(ReunionSameAction)
     */
    private void choseReunionSameAction(List<ReunionSameAction> actions) {
        for (int i = 0; i < actions.size(); i++) {
            ReunionSameAction action = actions.get(i);
            addButtonToPane(action.getActionName(), new ClientEventHandler(new SelectActionButton(game, this, action)),
                    buttonPane, i * 100 + 50, 0);
        }
    }

    /**
     * called when we add a button in the interface
     * @param content content of the button
     * @param listener listener of the button
     * @param pane pane where we add the button
     * @param offsetX move the button from the base position of the pane to the left (when offsetX is negative) or on
     *                the right (when offsetY is positive)
     * @param offsetY move the button from the base position of the pane to the up (when offsetX is negative) or on
     *                the down (when offsetY is positive)
     */
    private void addButtonToPane(String content, EventHandler<ActionEvent> listener, Pane pane, int offsetX, int offsetY) {
        Button button = new Button(content);
        button.setOnAction(listener);
        button.setPrefSize(85, 35);
        button.setStyle("-fx-background-color: #a96806;");
        button.setTextFill(javafx.scene.paint.Color.WHITE);
        button.setLayoutX(offsetX - button.getPrefWidth() / 2);
        button.setLayoutY(offsetY);
        pane.getChildren().add(button);
    }


    /**
     * Create content of the stage
     * @return the parent element to set to the stage
     */
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
        this.buttonPane = new Pane();
        buttonPane.setLayoutX(0);
        buttonPane.setLayoutY(600);
        if(game.getCurrentPlayer() instanceof HumanPlayer) {
            if(this.selectedReunionAction == null) {
                choseReunionSameAction(player.generateAvailableActions());
            } else {
                choseDirectionAction(selectedReunionAction);
            }
        } else {
            addButtonToPane("SUIVANT", new ClientEventHandler(nextPlayerButtonListener), buttonPane, (int) (principalPane.getPrefWidth() / 2), 0);
        }

        principalPane.getChildren().add(buttonPane);
        principalPane.setBackground(bg);
        return principalPane;
    }

    /**
     * Add grid element to the principal pane
     * @param object object to add to the pane
     * @param principalPane pane where we'll add the object
     * @param i
     * @param j
     */
    public void addToPrincipalPanel(Object object, Pane principalPane, int i, int j) {
        StackPane sp = Cell.setImageObject(object, game);
        sp.setLayoutY(i * cellSize);
        sp.setLayoutX(j * cellSize);
        principalPane.getChildren().add(sp);
    }

    /**
     * Create the right pane
     * @param principalPane principal pane where we'll add the left down pane
     */
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

    /**
     * Build left down pane (list all players information)
     * @param type
     * @param playerNumber
     * @return
     */
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

    /**
     * build left down pane and move it to its position
     * @param principalPane the principal pane where we'll add the left down pane
     */
    public void putMoveTextPane(Pane principalPane){
        StackPane stateMoveTextPane = showMoveText();
        stateMoveTextPane.setLayoutY(480);
        principalPane.getChildren().add((stateMoveTextPane));
    }

    /**
     * Build the left down pane (contains current player information)
     * @return the built pane
     */
    public StackPane showMoveText() {
        StackPane subSp = new StackPane();
        String action = game.getSelectedAction() == null ? "null" : game.getSelectedAction().getClass().getSimpleName();
        String s = "Vous \u00EAtes \u00E0 la position " + player.getPosition().toString();
        if(game.getPreviousPlayer() != null) {
            s = game.getPreviousPlayer() +  " : " + (game.getPreviousPlayer().getId()+1) + "\n" +
                    "Vient de jouer : " + action + "\n";
        }

        Text t = new Text(s);
        t.setFill(Color.WHITE);
        Rectangle r = new Rectangle();
        r.setWidth(478);
        r.setHeight(165);
        r.setFill(dark);
        subSp.getChildren().addAll(r,t);
        return subSp;
    }

    public void setSelectedReunionAction(ReunionSameAction selectedReunionAction) {
        this.selectedReunionAction = selectedReunionAction;
    }

    public NextPlayerButtonListener getNextPlayerButtonListener() {
        return nextPlayerButtonListener;
    }
}
