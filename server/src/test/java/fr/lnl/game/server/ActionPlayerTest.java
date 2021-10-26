package fr.lnl.game.server;

import fr.lnl.game.server.games.Game;
import fr.lnl.game.server.games.grid.Grid;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ActionPlayerTest {

    private Grid grid;
    private Game game;

    @BeforeEach
    public void mock() {
        Mock mock = new Mock();
        this.grid = mock.grid;
        this.game = mock.game;
    }

    // TODO: 21/10/2021 Vérifier sur Move effectue la bonne action en pensant a appeler isPossible() avant et
    //  en checkant son résultat
    @Test
    public void moveActionTest() {
        Assertions.assertEquals(game.getPlayers().get(0), game.getCurrentPlayer());
    }

}
