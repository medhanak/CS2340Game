package main;

import javafx.scene.layout.Pane;
////
public class WelcomeController {

    private Pane view;

    public WelcomeController(Pane pane) {
        this.view = pane;
    }

    public void goToConfig() {
        Main.changeView(new ConfigView());
    }
}
