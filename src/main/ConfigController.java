package main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
////
public class ConfigController {

    private ConfigView view;
    private int[] tempstats;
    private Weapon tempweap;
    private Image tempsprite;
    private String playerName;
    private Player player1;
    private boolean charSelect = false;
    private boolean nameSelect = false;
    private boolean diffSelect = false;

    public ConfigController(ConfigView pane) {
        this.view = pane;
    }

    public void finishSetup() {
        if (charSelect && nameSelect && diffSelect) {
            Player player1 = new Player(playerName, tempstats, tempsprite);
            Inventory startinventory = new Inventory(tempweap);
            RoomDict rooms = new RoomDict();
            MonsterDict monsters = new MonsterDict(Game.getType());
            Map map = new Map();
            Main.changeView(new GameView(player1));
        } else {
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setContentText(
                    "Please enter a name, select a difficulty, and select a character");
            warning.showAndWait();
        }
    }

    public void updateInfo(String type) {
        charSelect = true;
        WeaponDict weaponDict = new WeaponDict();
        Circle charimage = new Circle(80);
        Circle startweapon = new Circle(50);
        VBox info = new VBox();
        info.setSpacing(20);
        charimage.setFill(Color.WHITE);
        startweapon.setFill(Color.WHITE);
        HBox starts = new HBox();
        starts.getChildren().add(startweapon);
        VBox statnames = new VBox();
        HBox stats = new HBox();
        stats.getChildren().addAll(statnames);
        Label characterLb = new Label("Your Character:");
        Label weaponLb = new Label("Your Weapon:");
        info.getChildren().addAll(characterLb, charimage, weaponLb, starts, stats);
        info.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(0.5),
                new Insets(-5, -5, -5, -5))));
        stats.setAlignment(Pos.CENTER);
        starts.setAlignment(Pos.CENTER);
        info.setAlignment(Pos.CENTER);
        switch (type) {
        case "Imperial":
            tempstats = new int[]{50, 5, 1, 5, 500};
            tempweap = new Weapon("LasgunWeapon.jpg");
            tempsprite = new Image("ImperialGuardPlayer.png", 25, 30, true, false);
            charimage.setFill(new ImagePattern(new Image("ImperialGuard.jpg")));
            startweapon.setFill(new ImagePattern(new Image("LasgunWeapon.jpg")));
            break;
        case "Marine":
            tempstats = new int[]{200, 20, 2, 1, 100};
            tempweap = new Weapon("ChainswordWeapon.jpg");
            tempsprite = new Image("SpaceMarinePlayer.png", 25, 30, true, false);
            charimage.setFill(new ImagePattern(new Image("SpaceMarine.jpg")));
            startweapon.setFill(new ImagePattern(new Image("ChainswordWeapon.jpg")));
            break;
        case "Tau":
            tempstats = new int[]{100, 10, 3, 3, 300};
            tempweap = new Weapon("PulseRifleWeapon.jpg");
            tempsprite = new Image("TauWarriorPlayer.png", 25, 30, true, false);
            charimage.setFill(new ImagePattern(new Image("TauWarrior.jpg")));
            startweapon.setFill(new ImagePattern(new Image("PulseRifleWeapon.jpg")));
            break;
        case "Eldar":
        default:
            tempstats = new int[]{100, 30, 2, 1, 0};
            tempweap = new Weapon("WitchbladeWeapon.jpg");
            tempsprite = new Image("EldarWizardPlayer.png", 25, 30, true, false);
            charimage.setFill(new ImagePattern(new Image("EldarWizard.jpg")));
            startweapon.setFill(new ImagePattern(new Image("WitchbladeWeapon.jpg")));
            break;
        }

        //stats
        Label strength = new Label("Strength: " + tempstats[1]);
        Label speed = new Label("Speed: " + tempstats[2]);
        Label health = new Label("Health: " + tempstats[0]);
        Label lives = new Label("Lives: " + tempstats[3]);
        Label money = new Label("Money: " + tempstats[4]);
        statnames.getChildren().addAll(health, strength, speed, money, lives);
        statnames.setAlignment(Pos.CENTER);
        info.setAlignment(Pos.CENTER);
        view.setRight(info);
        BorderPane.setAlignment(info, Pos.CENTER);
    }
    public void changeShip(String ship) {
        diffSelect = true;
        BackgroundSize bsize = new BackgroundSize(1, 1, false, false, false, true);
        switch (ship) {
        case "Ork Ship":
            Game.setType(ship);
            Background orkship = new Background(new BackgroundImage(new Image("OrkShip.jpg"),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER, bsize));
            view.setBackground(orkship);
            break;
        case "Necron Hulk":
            Game.setType(ship);
            Background necronship = new Background(new BackgroundImage(new Image("NecronShip.jpg"),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER, bsize));
            view.setBackground(necronship);
            break;
        case "Tyranid Hive":
        default:
            Game.setType(ship);
            Background tyranidship = new Background(new BackgroundImage(
                    new Image("TyranidShip.jpg"),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER, bsize));
            view.setBackground(tyranidship);
            break;
        }
    }

    public void setPlayerName(String name) {
        if (!name.isEmpty() && !name.isBlank()) {
            nameSelect = true;
            playerName = name;
        } else {
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setContentText("Name cannot be empty!");
            warning.showAndWait();
        }
    }

    public Player getPlayer1() {
        return player1;
    }
    public Weapon getTempweap() {
        return tempweap;
    }
}
