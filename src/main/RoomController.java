package main;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class RoomController {

    private static RoomView view;
    private static Room currentRoom = Map.getEntrance();

    public RoomController(RoomView pane) {
        view = pane;
    }

    private static void buyItem(Tile tile) {
        String type = tile.getName();
        Item item;
        int price = ShopDict.getPrice(type);
        if (type.contains("Potion")) {
            item = new Potion(type);
        } else {
            item = new Weapon(type);
        }
        if (price > Player.getMoney()) {
            return;
        }
        Inventory.addItem(item);
        InventoryView.updateInventory();
        Player.setMoney(Player.getMoney() - price);
        GameController.updateMoney();
        Game.collectItem();
    }

    public static void grabItem(Tile tile) {
        String type = tile.getName();
        Item item;
        if (type.contains("Potion")) {
            item = new Potion(type);
        } else {
            item = new Weapon(type);
        }
        Inventory.addItem(item);
        InventoryView.updateInventory();
        tile.removeTile();
        Game.collectItem();
    }
    public static void pressExit(Tile tile) {
        if (currentRoom.getDistance(currentRoom.getFloor()[Player.getX()][Player.getY()],
                tile) > Player.getSpeed()) {
            return;
        }
        Room next = currentRoom;
        int x = currentRoom.getMapX();
        int y = currentRoom.getMapY();
        if (tile.getFloorX() == 0) {
            next = Map.getMap()[x - 1][y];
        }
        if (tile.getFloorX() == 10) {
            next = Map.getMap()[x + 1][y];
        }
        if (tile.getFloorY() == 0) {
            next = Map.getMap()[x][y - 1];
        }
        if (tile.getFloorY() == 10) {
            next = Map.getMap()[x][y + 1];
        }
        if (!(next.getOn()) && currentRoom.getNumMonsters() > 0 ||
                currentRoom.getType() == "challenge" && currentRoom.getNumMonsters() > 0) {
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setContentText("Door is locked!");
            warning.showAndWait();
            return;
        }
        Tile current = currentRoom.getFloor()[Player.getX()][Player.getY()];
        current.removeTile();
        Player.setX(5);
        Player.setY(5);
        Player.removeBuffs();
        currentRoom = next;
        Main.getController().changeRoom(next);
    }

    public static void updateRoom(Tile tile) {
        String type = tile.getName();
        Tile player = currentRoom.getFloor()[Player.getX()][Player.getY()];
        if (type.contains("Monster")) {
            if (null == Player.getWeapon()) {
                Alert warning = new Alert(Alert.AlertType.WARNING);
                warning.setContentText("You have no weapon equipped!");
                warning.showAndWait();
                return;
            }
            Monster target = new Monster();
            Monster[] mobs = currentRoom.getMobs();
            for (int i = 0; i < mobs.length; i++) {
                if (null == mobs[i]) {
                    continue;
                }
                if (mobs[i].getX() == tile.getFloorX() && mobs[i].getY() == tile.getFloorY()) {
                    target = mobs[i];
                    GameController.updateItem(target);
                }
            }
            double distance = currentRoom.getDistance(player, tile);
            if (distance <= Player.getWeapon().getRange()) {
                Player.attack(target);
                GameController.updateHealth();
            } else {
                Alert warning = new Alert(Alert.AlertType.WARNING);
                warning.setContentText("Target is out of range!");
                warning.showAndWait();
            }
        }
        if (type.contains("Potion") || (type.contains("Weapon"))) {
            if (currentRoom.getType() == "shop") {
                RoomController.buyItem(tile);
            } else {
                RoomController.grabItem(tile);
            }
        }
        if (type == "TileE.png") {
            RoomController.pressExit(tile);
            GameController.clearItem();
        }
        if (type == "Challenge.png") {
            GameController.clearItem();
            String ship = Game.getType();
            String[] shipList = new String[0];
            if (ship == "Ork Ship") {
                shipList = new String[] {"Ork RuntMonster.png", "FlyboyzMonster.png",
                        "Burna BoyzMonster.png", "WarbossMonster.png"};
            } else if (ship == "Tyranid Hive") {
                shipList = new String[] {"GauntMonster.png", "RipperMonster.png",
                        "Tyrant GuardMonster.png", "Hive TyrantMonster.png"};
            } else if (ship == "Necron Hulk") {
                shipList = new String[] {"Canoptek WraithMonster.png", "Necron WarriorMonster.png",
                        "Flayed OneMonster.png", "PhaeronMonster.png"};
            }
            int[] mobs = RoomDict.get(5);
            int num = 0;
            for (int i : mobs) {
                num += i;
            }
            currentRoom.setNumMonsters(num);
            currentRoom.generateMobs(shipList, mobs);
        }
        if (type == "tile") {
            Player.move(tile);
            GameController.clearItem();
        }
        for (int i = 0; i < currentRoom.getMobs().length; i++) {
            if (null == currentRoom.getMobs()[i]) {
                continue;
            }
            currentRoom.getMobs()[i].checkAttack();
        }
    }

    public static Room getCurrentRoom() {
        return currentRoom;
    }
}
