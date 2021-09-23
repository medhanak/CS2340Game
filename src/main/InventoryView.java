package main;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class InventoryView {

    private static GridPane view = new GridPane();

    public InventoryView() {
        int count = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                Button slot = new Button();
                slot.setStyle("-fx-background-image: url('InventoryTile.jpg')");
                slot.setMinSize(50, 50);
                slot.setMaxSize(50, 50);
                if (Inventory.getArray()[count] != null) {
                    Item item = Inventory.getArray()[count];
                    Image sprite = item.getSprite();
                    slot.setGraphic(new ImageView(sprite));
                }
                int finalcount = count;
                slot.setOnAction(e -> Inventory.useItem(finalcount));
                view.add(slot, j, i);
                count++;
            }
        }
    }

    public static void updateInventory() {
        int count = 0;
        Item[] array = Inventory.getArray();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                Button slot = new Button();
                for (Node node : view.getChildren()) {
                    if (view.getRowIndex(node) == i && view.getColumnIndex(node) == j) {
                        slot = (Button) node;
                    }
                }
                //slot.setStyle("-fx-background-image: url('InventoryTile.jpg')");
                if (array[count] != null) {
                    Item item = Inventory.getArray()[count];
                    Image sprite = item.getSprite();
                    slot.setGraphic(new ImageView(sprite));
                } else {
                    slot.setGraphic(null);
                }
                int finalCount = count;
                slot.setOnAction(e -> Inventory.useItem(finalCount));
                count++;
            }
        }
    }
    public static GridPane getView() {
        return view;
    }
}
