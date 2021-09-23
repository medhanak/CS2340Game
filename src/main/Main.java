package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
////
public class Main extends Application {
    private static Stage currentStage;
    private static GameController controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.currentStage = primaryStage;
        primaryStage.setTitle("Group54");
        primaryStage.setScene(new Scene(new WelcomeView(), 1000, 600));
        primaryStage.show();
    }

    public static void changeView(Pane pane) {
        currentStage.setScene(new Scene(pane, 1000, 600));
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static GameController getController() {
        return controller;
    }
    public static void setController(GameController acontroller) {
        controller = acontroller;
    }
    public static Stage getCurrentStage() {
        return currentStage;
    }
}
