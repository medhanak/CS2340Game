
package main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.scene.text.Font;

public class GameView extends BorderPane {

    private GameController controller = new GameController(this);
    private MapView mapview;
    private Rectangle healthGreen;
    private Rectangle healthRed;
    private Label health;
    private Rectangle weapSprite;
    private Label currDamage;
    private Label currMoney;
    private Label currStrength;
    private Label currSpeed;
    private Label currWeapon;
    private Label currRange;
    private Label item;
    private Rectangle itemSprite;
    private Label itemStat1;
    private Label itemStat2;

    public GameView(Player player) {
        Main.setController(controller);
        // Display background
        this.setPrefSize(1000, 600);
        BackgroundSize bsize = new BackgroundSize(1, 1, false, false, false, true);
        Background space = new Background(new BackgroundImage(new Image("space.jpeg"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, bsize));
        this.setBackground(space);

        // Display Room
        RoomView roomview = new RoomView(Map.getEntrance());
        this.setMargin(roomview, new Insets(0, 0, 0, 0));
        this.setCenter(roomview);

        // Display name
        Label name = new Label(Player.getName());
        name.setTextFill(Color.WHITE);
        name.setFont(new Font("Arial", 35));
        this.setTop(name);

        //Add stats pane
        //Map
        VBox allStats = new VBox();
        VBox mapPane = new VBox();
        mapview = new MapView(Map.getMap());
        mapview.setAlignment(Pos.CENTER);
        mapPane.setSpacing(20);
        mapPane.setAlignment(Pos.CENTER);
        Label mapLabel = new Label("Ship Map");
        mapLabel.setTextFill(Color.WHITE);
        this.setMargin(mapPane, new Insets(0, 0, 0, 0));
        mapPane.getChildren().addAll(mapLabel, mapview);
        allStats.getChildren().add(mapPane);

        //Item Viewer and Player Stats
        HBox specificStats = new HBox();
        specificStats.setBackground(new Background(new BackgroundFill(Color.SILVER, new CornerRadii(1),
                new Insets(0, 0, 0, 0))));

        //Item Viewer
        VBox viewer = new VBox();
        item = new Label();
        itemSprite = new Rectangle(150, 150, Color.TRANSPARENT);
        itemStat1 = new Label();
        itemStat2 = new Label();
        HBox itemStats = new HBox();
        itemStats.getChildren().addAll(itemStat1, new Label("   "), itemStat2);
        viewer.getChildren().addAll(item, itemSprite, itemStats);

        //Player Stats
        BorderPane playerStatsPane = new BorderPane();
        playerStatsPane.setTop(new Label("Player Stats"));
        weapSprite = new Rectangle(100, 100);
        weapSprite.setFill(Color.WHITE);
        weapSprite.setFill(new ImagePattern(Player.getWeapon().getSprite()));
        playerStatsPane.setRight(weapSprite);
        VBox playerStats = new VBox();

        health = new Label("Health: " + Player.getHealth() + "/200");
        HBox healthBar = new HBox();
        healthGreen = new Rectangle(player.getHealth() * .5, 15);
        healthGreen.setFill(Color.SEAGREEN);
        healthRed = new Rectangle(100 - (player.getHealth() * .5), 15);
        healthRed.setFill(Color.RED);
        healthBar.setSpacing(0);
        healthBar.getChildren().addAll(healthGreen, healthRed);

        currStrength = new Label("Strength: " + Player.getStrength());
        currSpeed = new Label("Speed: " + Player.getSpeed());
        currMoney = new Label("Money: $" + player.getMoney());
        String weapName = Player.getWeapon().getType();
        weapName = weapName.substring(0, weapName.length() - 10);
        currWeapon = new Label("Current Weapon: " + weapName);
        currDamage = new Label("Weapon Damage: " + Player.getWeapon().getDamage());
        currRange = new Label("Weapon Range: " + Player.getWeapon().getRange());
        playerStats.getChildren().addAll(health, healthBar, currStrength,
                currSpeed, currMoney, currWeapon, currDamage, currRange);
        playerStatsPane.setLeft(playerStats);
        specificStats.getChildren().addAll(viewer, playerStatsPane);
        allStats.getChildren().add(specificStats);

        //Inventory
        VBox inventoryBox = new VBox();
        InventoryView inventoryView = new InventoryView();
        Label inventory = new Label("Inventory");
        inventory.setTextFill(Color.WHITE);
        inventory.setAlignment(Pos.CENTER);
        inventoryBox.getChildren().addAll(inventory, InventoryView.getView());
        inventoryBox.setSpacing(20);
        inventoryBox.setAlignment(Pos.CENTER);
        allStats.getChildren().add(inventoryBox);
//        inventoryBox.setBackground(new Background(new BackgroundFill(Color.WHITE,
//                new CornerRadii(1), new Insets(-5, -5, -5, -5))));
        this.setRight(allStats);



        //Add current weapon and player's damage
//        VBox damage = new VBox();
//        weapSprite = new Rectangle(30, 20);
//        weapSprite.setFill(Color.WHITE);
//        weapSprite.setFill(new ImagePattern(Player.getWeapon().getSprite()));
//        currDamage = new Label("Current Strength: " + player.getStrength());
//        damage.setAlignment(Pos.CENTER);
//        damage.setSpacing(20);


//        //Add map
//        VBox map = new VBox();
//        Label mapLabel = new Label("Ship Map");
//        //mapLabel.setTextFill(Color.WHITE);
//        mapview = new MapView(Map.getMap());
//        map.setSpacing(20);
//        map.setAlignment(Pos.CENTER);
//        map.getChildren().addAll(mapLabel, mapview);
//        this.setMargin(map, new Insets(30, 30, 30, 30));
//        map.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(1),
//                new Insets(-5, -5, -5, -5))));
//
//        HBox damageAndMap = new HBox();
//        damageAndMap.getChildren().addAll(damage, map);
//        damageAndMap.setSpacing(30);


//        //Add inventory
//        VBox inventoryBox = new VBox();
//        InventoryView inventoryView = new InventoryView();
//        Label inventory = new Label("Inventory");
//        inventoryBox.getChildren().addAll(inventory, InventoryView.getView());
//        inventoryBox.setSpacing(20);
//        inventoryBox.setAlignment(Pos.CENTER);
//        inventoryBox.setBackground(new Background(new BackgroundFill(Color.WHITE,
//                new CornerRadii(1), new Insets(-5, -5, -5, -5))));
//
//        VBox playerStats = new VBox();
//        playerStats.setAlignment(Pos.CENTER);
//        playerStats.setSpacing(40);
//        playerStats.getChildren().addAll(damageAndMap, inventoryBox);
//        this.setRight(playerStats);
//        this.setMargin(playerStats, new Insets(20, 50, 30, 30));
    }


    public MapView getMapview() {
        return mapview;
    }

    public Rectangle getHealthGreen() {
        return healthGreen;
    }
    public Rectangle getHealthRed() {
        return healthRed;
    }
    public Label getHealth() {
        return health;
    }
    public Rectangle getWeapSprite() {
        return weapSprite;
    }
    public Label getCurrDamage() {
        return currDamage;
    }
    public Label getCurrMoney() {
        return currMoney;
    }
    public Label getCurrStrength() {
        return currStrength;
    }
    public Label getCurrSpeed() {
        return currSpeed;
    }
    public Label getCurrWeapon() {
        return currWeapon;
    }
    public Label getCurrRange() {
        return currRange;
    }
    public Label getItem() {
        return item;
    }

    public Rectangle getItemSprite() {
        return itemSprite;
    }

    public Label getItemStat1() {
        return itemStat1;
    }

    public Label getItemStat2() {
        return itemStat2;
    }

}