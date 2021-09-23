package main;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
////
public class WelcomeView extends BorderPane {
    private WelcomeController controller = new WelcomeController(this);

    public WelcomeView() {

        ImageView bg = new ImageView(new Image("welcomebg.jpg"));
        bg.setFitHeight(600);
        bg.setFitWidth(1000);
        this.getChildren().add(bg);

        Label label = new Label("SPACESHIP CRAWLERS");
        label.setFont(Font.font("Calibri", 55));
        label.setTextFill(Color.ALICEBLUE);
        label.setPadding(new Insets(30));

        StackPane header = new StackPane(label);
        this.setTop(header);

        Button startButton = new Button();
        startButton.setText("Start");
        startButton.setOnAction(e -> controller.goToConfig());

        Button howToPlay = new Button();
        howToPlay.setText("How to Play");

        Stage instructStage = new Stage();
        instructStage.setTitle("How To Play");
        instructStage.setHeight(200);
        instructStage.setWidth(400);

        Text instructions = new Text("Navigate through the spaceship and defeat all the enemies!");

        BorderPane instructPane = new BorderPane();
        instructPane.setCenter(instructions);

        Scene instructScene = new Scene(instructPane);
        instructStage.setScene(instructScene);


        howToPlay.setOnAction(e -> instructStage.show());

        Button settings = new Button();
        settings.setText("Settings");

        VBox buttons = new VBox();
        buttons.setSpacing(20);
        buttons.getChildren().addAll(startButton, howToPlay, settings);
        buttons.setAlignment(Pos.CENTER);

        this.setCenter(buttons);
    }
}
