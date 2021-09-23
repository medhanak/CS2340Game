package main;

import javafx.scene.image.Image;
////
public class Player {
    private static String name;
    private static int[] base;
    private static int health;
    private static int strength;
    private static int speed;
    private static int money;
    private static int lives;
    private static Weapon weapon;
    private static int x = 5;
    private static int y = 5;
    private static Image sprite;

    public Player(String name, int[] stats, Image sprite) {
        this.name = name;
        this.base = stats;
        this.health = stats[0];
        this.strength = stats[1];
        this.speed = stats[2];
        this.money = stats[4];
        this.lives = stats[3];
        this.sprite = sprite;
    }

    public static void attack(Monster target) {
        double damage = ((double) strength / 10) * weapon.getDamage();
        target.takeDamage((int) damage);
    }

    public static void move(Tile target) {
        int currentX = Player.getX();
        int currentY = Player.getY();
        Tile currentTile = RoomController.getCurrentRoom().getFloor()[currentX][currentY];
        double distance = RoomController.getCurrentRoom().getDistance(currentTile, target);
        if (distance <= Player.getSpeed()) {
            target.changeTile(sprite.getUrl());
            currentTile.removeTile();
            Player.setX(target.getFloorX());
            Player.setY(target.getFloorY());
        }
    }
    // Setters
    public static void setName(String inName) {
        name =  inName;
    }
    public static void setHealth(int inHealth) {
        health =  inHealth;
    }
    public static void setStrength(int inStrength) {
        strength =  inStrength;
    }
    public static void setSpeed(int inSpeed) {
        speed =  inSpeed;
    }
    public static void setMoney(int inMoney) {
        money =  inMoney;
    }
    public static void setLives(int inLives) {
        lives =  inLives;
    }
    public static void setWeapon(Weapon inWeapon) {
        weapon =  inWeapon;
    }
    public static void setX(int inX) {
        x = inX;
    }
    public static void setY(int inY) {
        y = inY;
    }



    // Getters
    public static int getMoney() {
        return money;
    }
    public static String getName() {
        return name;
    }
    public static int getHealth() {
        return health;
    }
    public static int getStrength() {
        return strength;
    }
    public static int getSpeed() {
        return speed;
    }
    public static int getLives() {
        return lives;
    }
    public static Weapon getWeapon() {
        return weapon;
    }
    public static int getX() {
        return x;
    }
    public static int getY() {
        return y;
    }
    public static Image getSprite() {
        return sprite;
    }

    public static void removeBuffs() {
        strength = base[1];
        speed = base[2];
        GameController.updateStats();
    }
}
