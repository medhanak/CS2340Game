package main;
//
import java.util.Random;
////
public class Map {

    private static Room[][] dungeon;
    private static Room entrance;
    private static Room exit;
    private static Room shop;


    public Map() {
        dungeon = new Room[11][11];
        //initialize key variables for generation
        Random rand = new Random();
        double limit = rand.nextInt(5);
        limit = limit * Game.getMultiplier();
        int count = 5;
        int x = rand.nextInt(8) + 1;
        int y = rand.nextInt(8) + 1;
        entrance = new Room(x, y, "entrance");
        dungeon[x][y] = entrance;
        entrance.setOn(true);
        dungeon[x - 1][y] = new Room(x - 1, y, 1);
        dungeon[x + 1][y] = new Room(x + 1, y, 1);
        dungeon[x][y + 1] = new Room(x, y + 1, 1);
        dungeon[x][y - 1] = new Room(x, y - 1, 1);
        int die = rand.nextInt(4);
        if (die == 0) {
            x++;
        } else if (die == 1) {
            x--;
        } else if (die == 2) {
            y++;
        } else if (die == 3) {
            y--;
        }
        int distance = 1;
        double total = 5;
        double diff = total/5;
        //while there are less than the max rooms, generates a new room adjacent to the current room
        while (distance < 6 && total < 40) {
            while (count < limit * Game.getMultiplier() && total < 40) {
                die = rand.nextInt(4);
                diff = total/5;
                if (total % 7 == 0) {
                    dungeon[x][y] = new Room(x, y, "challenge");
                } else if (total % 10 == 0) {
                    dungeon[x][y] = new Room(x, y, "shop");
                }
                //1/4 chance to generate a room above the current room
                if (die == 0) {
                    if (y != 0 && (null == dungeon[x][y - 1])) {
                        dungeon[x][y - 1] = new Room(x, y - 1, diff);
                        y--;
                        count++;
                    } else {
                        die = rand.nextInt(3) + 1;
                    }
                }
                //1/4 chance to generate a room below the current room
                if (die == 1) {
                    if (y != 10 && (null == dungeon[x][y + 1])) {
                        dungeon[x][y + 1] = new Room(x, y + 1, diff);
                        y++;
                        count++;
                    } else {
                        die = rand.nextInt(2) + 2;
                    }
                }
                //1/4 chance to generate a room to the left of the current room
                if (die == 2) {
                    if (x != 0 && (null == dungeon[x - 1][y])) {
                        dungeon[x - 1][y] = new Room(x - 1, y, diff);
                        x--;
                        count++;
                    } else {
                        die = 3;
                    }
                }
                //1/4 chance to generate a room to the right of the current room
                if (die == 3) {
                    if (x != 10 && (null == dungeon[x + 1][y])) {
                        dungeon[x + 1][y] = new Room(x + 1, y, diff);
                        count++;
                    }
                }
                //if current room is surrounded, find new room
                boolean surround = checkIfSurrounded(x, y);
                while (surround) {
                    if (y != 0) {
                        y--;
                    } else if (y != 10) {
                        y++;
                    } else if (x != 0) {
                        x--;
                    } else if (x != 10) {
                        x++;
                    }
                    checkIfSurrounded(x, y);
                }
                total++;
            }
            distance = (int) getDistance(entrance, dungeon[x][y]);
            count = count - 2;
        }
        dungeon[x][y] = new Room(x, y, "exit");
        exit = dungeon[x][y];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (null != dungeon[i][j]) {
                    boolean[] exits = Map.checkExits(i, j);
                    dungeon[i][j].setExits(exits);
                }
            }
        }
    }

    private boolean checkIfSurrounded(int x, int y) {
        boolean[] walls = new boolean[] {false, false, false, false};
        //check walls
        if (y == 0 || null != dungeon[x][y - 1]) {
            walls[0] = true;
        }
        if (y == 10 || null != dungeon[x][y + 1]) {
            walls[1] = true;
        }
        if (x == 0 || null != dungeon[x - 1][y]) {
            walls[2] = true;
        }
        if (x == 10 || null != dungeon[x + 1][y]) {
            walls[3] = true;
        }
        if (walls.equals(new boolean[] {true, true, true, true})) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean[] checkExits(int x, int y) {
        boolean[] exits = {false, false, false, false};
        //if there is a room above
        if (y > 0 && null != dungeon[x][y - 1]) {
            exits[0] = true;
        }
        //if there is a room below
        if (y < 10 && null != dungeon[x][y + 1]) {
            exits[1] = true;
        }
        //if there is a room to the left
        if (x > 0 && null != dungeon[x - 1][y]) {
            exits[2] = true;
        }
        //if there is a room to the right
        if (x < 10 && null != dungeon[x + 1][y]) {
            exits[3] = true;
        }
        return exits;
    }
    public double getDistance(Room start, Room end) {
        int legY = end.getMapY() - start.getMapY();
        int legX = end.getMapX() - start.getMapX();
        return Math.hypot(legX, legY);
    }
    public static Room[][] getMap() {
        return dungeon;
    }
    public static Room getEntrance() {
        return entrance;
    }
    public static Room getExit() {
        return exit;
    }
}
