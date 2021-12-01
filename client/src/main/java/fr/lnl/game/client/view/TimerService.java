package fr.lnl.game.client.view;

import fr.lnl.game.client.App;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;

public class TimerService extends ScheduledService<Integer> {
    @Override
    protected Task<Integer> createTask() {
        return new Task<>() {
            protected Integer call() {
                if(App.viewManager.getNeedUpdate()) {
                    App.viewManager.updateView();
                }
                return 0;
            }
        };
    }
}
