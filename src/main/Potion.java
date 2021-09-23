package main;

import javafx.scene.image.Image;

public class Potion extends Item {
    private String type;
    private int healthBoost;
    private int damageBoost;
    private int speedBoost;
    private Image sprite;

    public Potion(String type) {
        this.type = type;
        sprite = new Image(type, 20, 20, true, true);
        if (type.contains("Health")) {
            healthBoost = (int) (Math.random() * 20 + 10);
        } else if (type.contains("Attack")) {
            damageBoost = (int) (Math.random() * 10 + 10);
        } else {
            speedBoost = (int) (Math.random() * 3 + 1);
        }
    }
    public String getType() {
        return type;
    }
    public int getDamageBoost() {
        return damageBoost;
    }
    public int getHealthBoost() {
        return healthBoost;
    }
    public int getSpeedBoost() {
        return speedBoost;
    }
    public Image getSprite() {
        return sprite;
    }
}
