package main;

import javafx.scene.image.Image;

import java.util.Random;

////
public class Weapon extends Item {

    private String name;
    private Image sprite;
    private int damage;
    private int range;

    public Weapon() {
        this.name = "Bolt Pistol";
        this.sprite = new Image("BoltPistolWeapon.png", 20, 20, true, true);
        int[] stats = (int[]) WeaponDict.getDict().get("BoltPistolWeapon.png");
        damage = stats[0];
        range = stats[1];
    }
    public Weapon(String url) {
        this.sprite = new Image(url, 20, 20, true, true);
        this.name = url;
        int[] stats = (int[]) WeaponDict.getDict().get(url);
        damage = stats[0];
        range = stats[1];
    }
    public static Weapon randomWeapon(int rarity) {
        Object[] keys = WeaponDict.getDict().keySet().toArray();
        int random;
        String test;
        while (true) {
            random = new Random().nextInt(keys.length);
            test = (String) keys[random];
            int[] weapon = (int[]) WeaponDict.getDict().get(test);
            if (weapon[2] == rarity) {
                return new Weapon(test);
            }
        }
    }

    public String getType() {
        return this.name;
    }

    public int getRange() {
        return range;
    }
    public void setRange(int range) {
        this.range = range;
    }

    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Image getSprite() {
        return sprite;
    }
}
