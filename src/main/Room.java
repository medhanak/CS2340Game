package main;

import java.lang.Math;

public class Room {

    private boolean on = false;
    private final Room[][] parent = Map.getMap();
    private final Tile[][] floor = new Tile[11][11];
    private Monster[] mobs;
    private boolean top = false;
    private boolean bottom = false;
    private boolean right = false;
    private boolean left = false;
    private final int mapX;
    private final int mapY;
    private int numMonsters;
    private boolean isEntrance = false;
    private String type;

    public Room(int x, int y) {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                floor[i][j] = new Tile(i, j);
            }
        }
        this.mapX = x;
        this.mapY = y;
        int pX = Player.getX();
        int pY = Player.getY();
        floor[pX][pY] = new Tile(Player.getSprite(), pX, pY);
        mobs = new Monster[0];
    }
    public Room(int x, int y, Monster[] mobs) {
        this(x, y);
        this.mobs = mobs;
        numMonsters = mobs.length;
        for (int i = 0; i < mobs.length; i++) {
            int mX = mobs[i].getX();
            int mY = mobs[i].getY();
            floor[mX][mY] = new Tile(mobs[i].getSprite(), mX, mY);
        }
    }
    public Room(int x, int y, double difficulty) {
        this(x, y);
        String ship = Game.getType();
        double random = Math.random();
        if (random < .4) {
            difficulty = difficulty + 1;
        } else if (random < .7) {
            difficulty = difficulty - 1;
        }
        if (difficulty > 5) {
            difficulty = 5;
        }
        int[] mobList = (int[]) RoomDict.getDict().get(1);
        String[] shipList = new String[] {"Ork RuntMonster.png", "FlyboyzMonster.png",
                "Burna BoyzMonster.png", "WarbossMonster.png"};
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
        if (difficulty < 2) {
            mobList = (int[]) RoomDict.getDict().get(1);
            for (int i = 0; i < mobList.length; i++) {
                numMonsters += mobList[i];
            }
        } else if (difficulty < 3) {
            mobList = (int[]) RoomDict.getDict().get(2);
            for (int i = 0; i < mobList.length; i++) {
                numMonsters += mobList[i];
            }
        } else if (difficulty < 4) {
            mobList = (int[]) RoomDict.getDict().get(3);
            for (int i = 0; i < mobList.length; i++) {
                numMonsters += mobList[i];
            }
        } else if (difficulty < 5) {
            mobList = (int[]) RoomDict.getDict().get(4);
            for (int i = 0; i < mobList.length; i++) {
                numMonsters += mobList[i];
            }
        } else if (difficulty == 5) {
            mobList = (int[]) RoomDict.getDict().get(4);
            for (int i = 0; i < mobList.length; i++) {
                numMonsters += mobList[i];
            }
        }
        generateMobs(shipList, mobList);
    }

    public Room(int x, int y, String type) {
        this(x, y);
        this.type = type;
        if (type == "entrance") {
            return;
        } else if (type == "exit") {
            if (Game.getType() == "Ork Ship") {
                mobs = new Monster[] {new Monster("WarbossMonster.png")};
            } else if (Game.getType() == "Necron Hulk") {
                mobs = new Monster[] {new Monster("PhaeronMonster.png")};
            } else if (Game.getType() == "Tyranid Hive") {
                mobs = new Monster[] {new Monster("Hive TyrantMonster.png"),
                        new Monster("Tyrant GuardMonster.png"), new Monster("Tyrant GuardMonster.png")};
            }
        } else if (type == "challenge") {
            floor[8][8] = new Tile("Challenge.png", 8, 8);
        } else if (type == "shop") {
            ShopDict shop = new ShopDict();
            floor[2][2].changeTile("RedHealthPotion.png");
            floor[5][2].changeTile("GreenAttackPotion.png");
            floor[8][2].changeTile("YellowSpeedPotion.png");
            floor[2][8].changeTile(Weapon.randomWeapon(3).getType());
            floor[5][8].changeTile(Weapon.randomWeapon(2).getType());
            floor[8][8].changeTile(Weapon.randomWeapon(1).getType());
        }
        for (int i = 0; i < mobs.length; i++) {
            int mX = mobs[i].getX();
            int mY = mobs[i].getY();
            floor[mX][mY] = new Tile(mobs[i].getSprite(), mX, mY);
        }
    }

    public void generateMobs(String[] shipList, int[] mobList) {
        mobs = new Monster[numMonsters];
        int count = 0;
        int place = 0;
        //iterates through the 1-4 ranks of mobs
        for (int i : mobList) {
            //iterates through the number of mobs in each rank
            for (int j = 0; j < i; j++) {
                //adds a new mob of rank place to mobs and updates count
                mobs[count] = new Monster(shipList[place]);
                count++;
            }
            //increments rank
            place++;
        }
        for (int i = 0; i < mobs.length; i++) {
            int mX = mobs[i].getX();
            int mY = mobs[i].getY();
            floor[mX][mY] = new Tile(mobs[i].getSprite(), mX, mY);
        }
    }

    public void resetRoom() {
        if (type == "shop" || type == "challenge") {
            return;
        }
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                floor[i][j] = new Tile(i, j);
            }
        }
        if (numMonsters > 0) {
            for (int i = 0; i < mobs.length; i++) {
                if (null == mobs[i]) {
                    continue;
                }
                int mX = mobs[i].getX();
                int mY = mobs[i].getY();
                floor[mX][mY] = new Tile(mobs[i].getSprite(), mX, mY);
            }
        }
        int pX = Player.getX();
        int pY = Player.getY();
        floor[pX][pY] = new Tile(Player.getSprite(), pX, pY);
        boolean[] exits = Map.checkExits(mapX, mapY);
        this.setExits(exits);
    }
    public double getDistance(Tile start, Tile end) {
        int legY = end.getFloorY() - start.getFloorY();
        int legX = end.getFloorX() - start.getFloorX();
        return Math.hypot(legX, legY);
    }
    public void clearRoom() {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                floor[i][j] = new Tile(i, j);
            }
        }
        int pX = Player.getX();
        int pY = Player.getY();
        floor[pX][pY] = new Tile(Player.getSprite(), pX, pY);
        boolean[] exits = Map.checkExits(mapX, mapY);
        this.setExits(exits);
    }
    public void removeMob(Monster target) {
        for (int i = 0; i < mobs.length; i++) {
            if (target == mobs[i]) {
                int mX = mobs[i].getX();
                int mY = mobs[i].getY();
                mobs[i] = null;
                numMonsters--;
                if (type == "challenge" && numMonsters == 0) {
                    Player.setMoney(Player.getMoney() + 300);
                    Game.earnMoney(300);
                    GameController.updateMoney();
                    resetRoom();
                }
                double rand = Math.random();
                if (rand <= .5) {
                    floor[mX][mY].removeTile();
                } else if (rand < .6) {
                    floor[mX][mY].changeTile("YellowSpeedPotion.png");
                } else if (rand < .8) {
                    floor[mX][mY].changeTile("RedHealthPotion.png");
                } else if (rand < .9) {
                    floor[mX][mY].changeTile("GreenAttackPotion.png");
                } else {
                    double rand2 = Math.random();
                    Weapon newWeap;
                    if (rand2 < .5) {
                        newWeap = Weapon.randomWeapon(3);
                    } else if (rand2 < .8) {
                        newWeap = Weapon.randomWeapon(2);
                    } else {
                        newWeap = Weapon.randomWeapon(1);
                    }
                    floor[mX][mY].changeTile(newWeap.getType());
                }
            }
        }
    }

    public void removeMob(Monster target, Item drop) {
        for (int i = 0; i < mobs.length; i++) {
            if (target == mobs[i]) {
                int mX = mobs[i].getX();
                int mY = mobs[i].getY();
                mobs[i] = null;
                numMonsters--;
                floor[mX][mY].changeTile(drop.getType());
            }
        }
    }

    public boolean[] getExits() {
        boolean[] exits = new boolean[] {top, bottom, left, right};
        return exits;
    }
    public void setExits(boolean[] exits) {
        this.top = exits[0];
        this.bottom = exits[1];
        this.left = exits[2];
        this.right = exits[3];
        if (top) {
            floor[5][0] = new Tile("TileE.png", 5, 0);
        }
        if (bottom) {
            floor[5][10] = new Tile("TileE.png", 5, 10);
        }
        if (left) {
            floor[0][5] = new Tile("TileE.png", 0, 5);
        }
        if (right) {
            floor[10][5] = new Tile("TileE.png", 10, 5);
        }
    }

    public int getMapX() {
        return mapX;
    }
    public String getType() {
        return type;
    }
    public int getMapY() {
        return mapY;
    }
    public int getNumMonsters() {
        return numMonsters;
    }
    public Tile[][] getFloor() {
        return floor;
    }
    public boolean getOn() {
        return this.on;
    }
    public void setOn(boolean exists) {
        this.on = exists;
    }
    public Monster[] getMobs() {
        return mobs;
    }
    public void setNumMonsters(int num) {
        numMonsters = num;
    }
    public void setType(String type) { this.type = type;}
    public void setMobs(Monster[] mobs) {
        this.mobs = mobs;
    }
}
