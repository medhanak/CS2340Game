package main;

import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Color;

import java.awt.*;

////
public class GameController {

    private static GameView view;
    //Pane view;
    public GameController(GameView pane) {
        this.view = pane;
    }

    public void changeRoom(Room room) {
        if (room.getOn()) {
            room.resetRoom();
        }
        room.setOn(true);
        view.getMapview().refreshMap();
        view.setCenter(new RoomView(room));
    }

    public static void updateHealth() {
        if (Player.getHealth() > 200) {
            view.getHealthGreen().setWidth(100);
            view.getHealthRed().setWidth(0);
        } else {
            view.getHealthGreen().setWidth(Player.getHealth() * .5);
            view.getHealthRed().setWidth(100 - (Player.getHealth() * .5));
        }
        view.getHealth().setText("Health: " + Player.getHealth() + "/200");
    }

    public static void updateWeapon() {
        Weapon weap = Player.getWeapon();
        view.getWeapSprite().setFill(new ImagePattern(weap.getSprite()));
        view.getCurrDamage().setText("Current Damage: " + weap.getDamage());
        view.getCurrRange().setText("Current Range: " + weap.getRange());
        view.getCurrWeapon().setText("Current Weapon: " + weap.getType().substring(0, weap.getType().length() - 10));
    }
    public static void updateStats() {
        view.getCurrStrength().setText("Strength: " + Player.getStrength());
        view.getCurrSpeed().setText("Speed: " + Player.getSpeed());
    }
    public static void updateMoney() {
        view.getCurrMoney().setText("Money: $" + Player.getMoney());
    }
    public static void updateItem(Item item) {
        view.getItem().setText(item.getType());
        view.getItemSprite().setFill(new ImagePattern(item.getSprite()));
        if (item.getType().contains("Potion")) {
            Potion pot = (Potion) item;
            if (item.getType().contains("Attack")) {
                view.getItemStat1().setText("Strength Boost: " + pot.getDamageBoost());
            } else if (item.getType().contains("Health")) {
                view.getItemStat1().setText("Health Boost: " + pot.getHealthBoost());
            } else if (item.getType().contains("Speed")) {
                view.getItemStat1().setText("Speed Boost: " + pot.getSpeedBoost());
            }
        } else if (item.getType().contains("Weapon")) {
            Weapon weap = (Weapon) item;
            view.getItemStat1().setText("Damage: " + weap.getDamage());
            view.getItemStat2().setText("Range: " + weap.getRange());
        }
    }
    public static void updateItem(Monster mob) {
        view.getItem().setText(mob.getType().substring(0, mob.getType().length() - 11));
        view.getItemSprite().setFill(new ImagePattern(mob.getSprite()));
        view.getItemStat1().setText("Damage: " + mob.getDamage());
        view.getItemStat2().setText("Health: " + mob.getHP());
    }
    public static void clearItem() {
        view.getItem().setText("");
        view.getItemSprite().setFill(Color.TRANSPARENT);
        view.getItemStat1().setText("");
        view.getItemStat2().setText("");
    }
}
