package fr.lnl.game.client.view;

import fr.lnl.game.client.listener.ButtonListener;
import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.player.Player;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Window extends AbstractView {

    private final Stage stage;

    public Window(Stage stage, Game game, Player player) {
        super(game, player);
        this.stage = stage;
    }

    public void show() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Hello, JavaFX version " + javafxVersion + ", running on Java " + javaVersion + ".");
        Label l2 = new Label("I'm " + player.getClass().getSimpleName() + " num " + player.getId() + " my class is " + player.getClassPlayer().toString());
        Button b = new Button("Click to see others players informations");
        b.setOnAction(new ClientEventHandler(new ButtonListener()));
        GridPane grid = new GridPane();
        grid.add(l, 0, 0);
        grid.add(l2, 0, 1);
        grid.add(b, 0, 2);
        Scene scene = new Scene(grid, 640, 480);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}
