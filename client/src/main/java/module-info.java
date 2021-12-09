/**
 * Client module, include every view and controller classes from MVC model
 */
module client {
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires server;
    exports fr.lnl.game.client;
    exports fr.lnl.game.client.listener;
    exports fr.lnl.game.client.view;
}
