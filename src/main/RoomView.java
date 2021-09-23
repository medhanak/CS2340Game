package main;

import javafx.scene.layout.GridPane;
////
public class RoomView extends GridPane {

    private RoomController controller = new RoomController(this);
    private Room myRoom;

    public RoomView(Room room) {
        Tile[][] floor = room.getFloor();
        for (int i = 0; i < floor.length; i++) {
            for (int j = 0; j < floor[i].length; j++) {
                this.add(floor[i][j], i, j);
            }
        }
    }
}
