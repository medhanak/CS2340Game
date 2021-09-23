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

public class EndView extends BorderPane {

    private EndController controller = new EndController(this);

    public EndView(String condition) {
        ImageView bg = new ImageView(new Image("welcomebg.jpg"));
        bg.setFitHeight(700);
        bg.setFitWidth(1000);
        this.getChildren().add(bg);

        Label end = new Label();
        end.setFont(Font.font("Calibri", 55));
        end.setTextFill(Color.ALICEBLUE);
        if (condition == "death") {
            end.setText("You died!");
        } else if (condition == "victory") {
            end.setText("You slew the boss!");
        }

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
        vbox.getChildren().addAll(end, playAgain, buttons);

        this.setCenter(vbox);
        VBox stats = new VBox();
        Label mobsKilled = new Label("Monsters Killed: " + Game.getMobsKilled());
        mobsKilled.setTextFill(Color.WHITE);
        Label moneyEarned = new Label("Total Money Earned: " + Game.getMoneyEarned());
        moneyEarned.setTextFill(Color.WHITE);
        Label potsDrunk = new Label("Potions Drunk: " + Game.getPotsDrank());
        potsDrunk.setTextFill(Color.WHITE);
        Label itemsCollected = new Label("Total Items Collected: " + Game.getItemsCollected());
        itemsCollected.setTextFill(Color.WHITE);
        stats.getChildren().addAll(mobsKilled, moneyEarned, potsDrunk, itemsCollected);
        this.setRight(stats);
    }
}
