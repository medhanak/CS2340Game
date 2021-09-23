package main;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DeathView extends BorderPane {

    private DeathViewController controller = new DeathViewController(this);

    public DeathView() {

        ImageView bg = new ImageView(new Image("welcomebg.jpg"));
        bg.setFitHeight(500);
        bg.setFitWidth(1000);
        this.getChildren().add(bg);

        Label youDied = new Label("YOU DIED!");
        youDied.setFont(Font.font("Calibri", 55));
        youDied.setTextFill(Color.ALICEBLUE);

        Label playAgain = new Label("Would you like to play again?");
        playAgain.setFont(Font.font("Calibri", 15));
        playAgain.setTextFill(Color.ALICEBLUE);

        Button yes = new Button("Yes");
        yes.setOnAction(e -> controller.goToConfig());
        Button no = new Button("No");

        HBox buttons = new HBox();
        buttons.setSpacing(20);
        buttons.getChildren().addAll(yes, no);

        VBox vbox = new VBox();
        vbox.setSpacing(40);
        vbox.getChildren().addAll(youDied, playAgain, buttons);

        this.setCenter(vbox);
    }

}
