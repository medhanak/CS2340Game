package main;

import javafx.scene.image.Image;

public class Monster {
    private int health;
    private int damage;
    private int speed;
    private int money;
    private Image sprite;
    private int x;
    private int y;
    private String type;


    public Monster() {
        health = 100;
        damage = 5;
        speed = 1;
        money = 5;
        this.sprite = new Image("TileM.png", 20, 20, true, true);
        int x = (int) (Math.random() * 10);
        int y = (int) (Math.random() * 10);
        if (x == 5 && y == 5) {
            y--;
        }
        this.x = x;
        this.y = y;
    }
    public Monster(String url) {
        int[] stats = (int[]) MonsterDict.getDict().get(url);
        this.health = stats[0];
        this.damage = stats[1];
        this.speed = stats[2];
        this.money = stats[3];
        this.sprite = new Image(url, 40, 40, true, true);
        int x = (int) (Math.random() * 10);
        int y = (int) (Math.random() * 10);
        if (x == 5 && y == 5) {
            y--;
        }
        this.x = x;
        this.y = y;
        type = url;
    }
    public void attack() {
        Player.setHealth(Player.getHealth() - damage);
        GameController.updateHealth();
        if (Player.getHealth() <= 0) {
            Main.changeView(new EndView("death"));
        }
    }

    public void checkAttack() {
        Tile player = RoomController.getCurrentRoom().getFloor()[Player.getX()][Player.getY()];
        Tile monster = RoomController.getCurrentRoom().getFloor()[this.x][this.y];
        if (RoomController.getCurrentRoom().getDistance(player, monster)
                <= 1) {
            attack();
        } else {
            move(player);
        }
    }

    public void move(Tile player) {
        Tile current = RoomController.getCurrentRoom().getFloor()[x][y];
        int pX = player.getFloorX();
        int pY = player.getFloorY();
        int tempX = x;
        int tempY = y;
        if (pX > x) {
            tempX = this.x + speed;
        } else if (pX < x) {
            tempX = this.x - speed;
        }
        if (pY > y) {
            tempY = this.y + speed;
        } else if (pY < y) {
            tempY = this.y - speed;
        }
        if (tempX > 10 || tempY > 10 || tempX < 0 || tempY < 0) {
            return;
        }
        Tile target = RoomController.getCurrentRoom().getFloor()[tempX][tempY];
        if (target.getName() != "tile" ) {
            return;
        }
        target.changeTile(sprite.getUrl());
        current.removeTile();
        this.x = tempX;
        this.y = tempY;
    }

    public void takeDamage(int damage) {
        this.health = health - damage;
        if (health <= 0) {
            if (type == "WarbossMonster.png" || type == "PhaeronMonster.png" || type == "Hive TyrantMonster.png") {
                Main.changeView(new EndView("victory"));
            }
            Player.setMoney(Player.getMoney() + money);
            Game.earnMoney(money);
            GameController.updateMoney();
            RoomController.getCurrentRoom().removeMob(this);
            Game.killMob();
        }
    }

    public int getHP() {
        return this.health;
    }
    public void setHP(int hp) {
        this.health = hp;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getX() {
        return this.x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getY() {
        return this.y;
    }
    public Image getSprite() {
        return sprite;
    }
    public String getType() {
        return type;
    }
    public int getDamage() {
        return damage;
    }
}
