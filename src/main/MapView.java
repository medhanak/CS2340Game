package main;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
////
public class MapView extends GridPane {

    private static Room[][] map;

    public MapView(Room[][] amap) {
        this.map = amap;
        int roomCount = 1;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (null != map[i][j] && map[i][j].getOn()) {
                    Button entrance = new Button();
                    entrance.setPrefSize(30, 30);
                    entrance.setStyle("-fx-background-color: yellow");
                    this.add(entrance, i, j);
                }
            }
        }
    }
    public void refreshMap() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (null != map[i][j] && map[i][j].getOn()) {
                    Button room = new Button();
                    room.setPrefSize(30, 30);
                    if (Map.getMap()[i][j].getType() == "entrance" || Map.getMap()[i][j].getType() == "exit") {
                        room.setStyle("-fx-background-color: yellow");
                    } else if (Map.getMap()[i][j] == RoomController.getCurrentRoom()) {
                        room.setStyle("-fx-background-color: green");
                    } else if (Map.getMap()[i][j].getType() == "challenge") {
                        room.setStyle("-fx-background-color: red");
                    } else {
                        room.setStyle("-fx-background-color: blue");
                    }
                    this.add(room, i, j);
                }
            }
        }
    }
}
