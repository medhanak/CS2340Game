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
////
public class WinnerView extends BorderPane {

    private WinnerController controller = new WinnerController(this);

    public WinnerView() {

        ImageView bg = new ImageView(new Image("welcomebg.jpg"));
        bg.setFitHeight(500);
        bg.setFitWidth(1000);
        this.getChildren().add(bg);

        Label youWin = new Label("YOU WIN!");
        youWin.setFont(Font.font("Calibri", 55));
        youWin.setTextFill(Color.ALICEBLUE);

        Label congrats = new Label("You defeated all the enemies and escaped the ship!");
        congrats.setFont(Font.font("Calibri", 15));
        congrats.setTextFill(Color.ALICEBLUE);

        Label playAgain = new Label("Would you like to play again?");
        playAgain.setFont(Font.font("Calibri", 15));
        playAgain.setTextFill(Color.ALICEBLUE);

        Button yes = new Button("Yes");
        yes.setOnAction(e -> controller.goToConfig());
        Button no = new Button("No");
        no.setOnAction(e -> Main.getCurrentStage().close());

        HBox buttons = new HBox();
        buttons.setSpacing(20);
        buttons.getChildren().addAll(yes, no);

        VBox vbox = new VBox();
        vbox.setSpacing(40);
        vbox.getChildren().addAll(youWin, congrats, playAgain, buttons);

        this.setCenter(vbox);

    }
}
