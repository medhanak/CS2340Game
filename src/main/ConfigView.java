package main;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
////
public class ConfigView extends BorderPane {
    private ConfigController controller = new ConfigController(this);

    public ConfigView() {

        //options
        Label title = new Label("Group54");
        this.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);
        VBox options = new VBox();
        options.setSpacing(30);
        options.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(options, Pos.TOP_LEFT);
        TextField nameentry = new TextField();
        nameentry.setMaxWidth(150);
        Button namebutton = new Button("Enter your name!");
        HBox namerow = new HBox(nameentry, namebutton);
        namebutton.setOnAction(e -> controller.setPlayerName(nameentry.getText()));
        namerow.setAlignment(Pos.CENTER);
        Label difficulty = new Label("Select a ship!");
        HBox diffrow = new HBox();
        Label characterlabel = new Label("Select a starting character!");
        HBox charrow = new HBox();
        options.getChildren().addAll(namerow, difficulty, diffrow, characterlabel, charrow);
        this.setCenter(options);

        //start button
        Button playButton = new Button("Play!");
        playButton.setOnAction(e -> controller.finishSetup());
        this.setBottom(playButton);
        BorderPane.setAlignment(playButton, Pos.CENTER);

        //difficulties/ships
        RadioButton easyork = new RadioButton("Ork Ship");
        RadioButton mediumnecron = new RadioButton("Necron Hulk");
        RadioButton hardtyranid = new RadioButton("Tyranid Hive");
        ToggleGroup difficulties = new ToggleGroup();
        easyork.setToggleGroup(difficulties);
        mediumnecron.setToggleGroup(difficulties);
        hardtyranid.setToggleGroup(difficulties);
        diffrow.getChildren().addAll(easyork, mediumnecron, hardtyranid);
        diffrow.setAlignment(Pos.CENTER);
        easyork.setOnAction(e -> controller.changeShip("Ork Ship"));
        mediumnecron.setOnAction(e -> controller.changeShip("Necron Hulk"));
        hardtyranid.setOnAction(e -> controller.changeShip("Tyranid Hive"));

        //characters
        ToggleGroup characters = new ToggleGroup();
        RadioButton imperial = new RadioButton("Imperial Guard");
        RadioButton marine = new RadioButton("Space Marine");
        RadioButton tau = new RadioButton("Tau Warrior");
        RadioButton eldar = new RadioButton("Eldar Wizard");
        imperial.setToggleGroup(characters);
        marine.setToggleGroup(characters);
        tau.setToggleGroup(characters);
        eldar.setToggleGroup(characters);
        charrow.getChildren().addAll(imperial, marine, tau, eldar);
        charrow.setAlignment(Pos.CENTER);
        imperial.setOnAction(e -> controller.updateInfo("Imperial"));
        marine.setOnAction(e -> controller.updateInfo("Marine"));
        tau.setOnAction(e -> controller.updateInfo("Tau"));
        eldar.setOnAction(e -> controller.updateInfo("Eldar"));
    }
}
