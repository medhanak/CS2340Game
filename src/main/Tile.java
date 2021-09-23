package main;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile extends Button {

    private String name;
    private int floorX;
    private int floorY;

    public Tile(String url, int x, int y) {
        this.setStyle("-fx-background-image: url('Tile.png')");
        ImageView image = new ImageView(new Image(url, 20, 20, true, true));
        this.setGraphic(image);
        this.setOnAction(e -> RoomController.updateRoom(this));
        this.setMinSize(50, 50);
        this.setMaxSize(50, 50);
        floorX = x;
        floorY = y;
        name = url;
    }
    public Tile(int x, int y) {
        this.setStyle("-fx-background-image: url('Tile.png')");
        this.setOnAction(e -> RoomController.updateRoom(this));
        this.setGraphic(null);
        this.setMinSize(50, 50);
        this.setMaxSize(50, 50);
        floorX = x;
        floorY = y;
        name = "tile";
    }

    public Tile(Image sprite, int x, int y) {
        this.setStyle("-fx-background-image: url('Tile.png')");
        ImageView image = new ImageView(sprite);
        this.setGraphic(image);
        this.setOnAction(e -> RoomController.updateRoom(this));
        this.setMinSize(50, 50);
        this.setMaxSize(50, 50);
        floorX = x;
        floorY = y;
        name = sprite.getUrl();
    }
    public void changeTile(String url) {
        ImageView image = new ImageView(new Image(url, 20, 20, true, true));
        this.setGraphic(image);
        name = url;
    }
    public void removeTile() {
        this.setGraphic(null);
        name = "tile";
    }

    public int getFloorX() {
        return floorX;
    }

    public int getFloorY() {
        return floorY;
    }

    public String getName() {
        return name;
    }
}