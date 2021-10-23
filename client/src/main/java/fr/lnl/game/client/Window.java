package fr.lnl.game.client;

import fr.lnl.game.server.games.Game;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Window extends AbstractView {

    private final Stage stage;

    public Window(Stage stage, Game game) {
        super(game);
        this.stage = stage;
    }

    public void show() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Hello, JavaFX version " + javafxVersion + ", running on Java " + javaVersion + ".");
        Scene scene = new Scene(new StackPane(l), 640, 480);
        stage.setScene(scene);
        stage.show();
    }
}
