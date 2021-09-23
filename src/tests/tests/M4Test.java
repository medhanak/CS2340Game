package tests.tests;

import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import main.*;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

public class M4Test extends ApplicationTest {

    private JFXPanel panel = new JFXPanel();
    private Room[][] map1;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage test1 = new Stage();
        Main welcome = new Main();
        welcome.start(test1);
    }

    @Test
    public void lockTest() {
        ConfigView config = new ConfigView();
        ConfigController confcontr = new ConfigController(config);
        clickOn("Start").write("Name");
        clickOn("Enter your name!");
        clickOn("Ork Ship");
        clickOn("Tau Warrior");
        clickOn("Play!");
        Room entrance = RoomController.getCurrentRoom();
        Tile[][] floor = RoomController.getCurrentRoom().getFloor();
        clickOn(floor[8][5]);
        clickOn(floor[10][5]);
        Room firstroom = RoomController.getCurrentRoom();
        boolean[] exits = RoomController.getCurrentRoom().getExits();
        boolean top = exits[0];
        boolean bottom = exits[1];
        boolean left = exits[2];
        boolean right = exits[3];
        floor = firstroom.getFloor();
        if (top) {
            clickOn(floor[5][3]);
            clickOn(floor[5][1]);
            clickOn(floor[5][0]);
        } else if (bottom) {
            clickOn(floor[5][8]);
            clickOn(floor[5][10]);
        } else if (right) {
            clickOn(floor[8][5]);
            clickOn(floor[10][5]);
        } else {
            clickOn(floor[2][5]);
            clickOn(floor[0][5]);
            floor = RoomController.getCurrentRoom().getFloor();
            clickOn(floor[2][5]);
            clickOn(floor[0][5]);
            floor = RoomController.getCurrentRoom().getFloor();
            if (top) {
                clickOn(floor[5][3]);
                clickOn(floor[5][1]);
                clickOn(floor[5][0]);
            } else if (bottom) {
                clickOn(floor[5][8]);
                clickOn(floor[5][10]);
            } else if (left) {
                clickOn(floor[2][5]);
                clickOn(floor[0][5]);
            }
        }
        assertEquals(firstroom, RoomController.getCurrentRoom());
    }

    @Test
    public void playerAttackTest() {
        ConfigView config = new ConfigView();
        ConfigController confcontr = new ConfigController(config);
        clickOn("Start").write("Name");
        clickOn("Enter your name!");
        clickOn("Ork Ship");
        clickOn("Tau Warrior");
        clickOn("Play!");
        Room entrance = RoomController.getCurrentRoom();
        Tile[][] floor = RoomController.getCurrentRoom().getFloor();
        clickOn(floor[8][5]);
        clickOn(floor[10][5]);
        Room firstroom = RoomController.getCurrentRoom();
        floor = firstroom.getFloor();
        Monster[] mobs = firstroom.getMobs();
        Monster target = mobs[0];
        int[] coords = {target.getX(), target.getY()};
        while (firstroom.getDistance(floor[Player.getX()][Player.getY()],
                floor[coords[0]][coords[1]]) > 2) {
            if (coords[0] > Player.getX()) {
                clickOn(floor[Player.getX() + 1][Player.getY()]);
            }
            if (coords[0] < Player.getX()) {
                clickOn(floor[Player.getX() - 1][Player.getY()]);
            }
            if (coords[1] > Player.getY()) {
                clickOn(floor[Player.getX()][Player.getY() + 1]);
            }
            if (coords[1] < Player.getX()) {
                clickOn(floor[Player.getX()][Player.getY() - 1]);
            }
        }
        Player.attack(target);
        assertEquals(target.getHP(), 5);
    }

    @Test
    public void testMonstersInRoom() {
        ConfigView test = new ConfigView();
        ConfigController imperial = new ConfigController(test);
        clickOn("Start").write("Name");
        clickOn("Enter your name!");
        clickOn("Ork Ship");
        clickOn("Imperial Guard");
        clickOn("Play!");
        int count = 0;
        Room[][] map = Map.getMap();

        for (int i = 0; i < map[0].length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] != null && map[i][j].getOn()) {
                    count = map[i][j].getNumMonsters();
                    assertTrue(count > 0 && count < 4);
                }
            }
        }
    }

//    @Test
//    public void testMonsterTypes() {
//        Monster mon1 = Room.chooseMonster(1);
//        Monster mon2 = Room.chooseMonster(2);
//        Monster mon3 = Room.chooseMonster(3);
//        assertEquals(mon1.getType(), 1);
//        assertEquals(mon2.getType(), 2);
//        assertEquals(mon3.getType(), 3);
//    }

    @Test
    public void monsterAttackTest() {
        ConfigView config = new ConfigView();
        ConfigController confcontr = new ConfigController(config);
        ConfigView test = new ConfigView();
        ConfigController imperial = new ConfigController(test);
        clickOn("Start").write("Name");
        clickOn("Enter your name!");
        clickOn("Ork Ship");
        clickOn("Imperial Guard");
        clickOn("Play!");
        Room entrance = RoomController.getCurrentRoom();
        Tile[][] floor = RoomController.getCurrentRoom().getFloor();
        clickOn(floor[6][5]);
        clickOn(floor[7][5]);
        clickOn(floor[8][5]);
        clickOn(floor[9][5]);
        clickOn(floor[10][5]);
        Room firstroom = RoomController.getCurrentRoom();
        floor = firstroom.getFloor();
        Monster[] mobs = firstroom.getMobs();
        Monster monster = mobs[0];
        int start = Player.getHealth();
        int[] coords = {monster.getX(), monster.getY()};
        while (firstroom.getDistance(floor[Player.getX()][Player.getY()],
                floor[coords[0]][coords[1]]) > 2) {
            if (coords[0] > Player.getX()) {
                clickOn(floor[Player.getX() + 1][Player.getY()]);
            }
            if (coords[0] < Player.getX()) {
                clickOn(floor[Player.getX() - 1][Player.getY()]);
            }
            if (coords[1] > Player.getY()) {
                clickOn(floor[Player.getX()][Player.getY() + 1]);
            }
            if (coords[1] < Player.getX()) {
                clickOn(floor[Player.getX()][Player.getY() - 1]);
            }
        }
        monster.attack();
        assertNotEquals(Player.getHealth(), start);
    }

    @Test
    public void playerDeathTest() {
        ConfigView config = new ConfigView();
        ConfigController confcontr = new ConfigController(config);
        clickOn("Start").write("Name");
        clickOn("Enter your name!");
        clickOn("Ork Ship");
        clickOn("Imperial Guard");
        clickOn("Play!");
        Room entrance = RoomController.getCurrentRoom();
        Tile[][] floor = RoomController.getCurrentRoom().getFloor();
        clickOn(floor[6][5]);
        clickOn(floor[7][5]);
        clickOn(floor[8][5]);
        clickOn(floor[9][5]);
        clickOn(floor[10][5]);
        Room firstroom = RoomController.getCurrentRoom();
        floor = firstroom.getFloor();
        Monster[] mobs = firstroom.getMobs();
        Monster monster = mobs[0];
        int[] coords = {monster.getX(), monster.getY()};
        while (firstroom.getDistance(floor[Player.getX()][Player.getY()],
                floor[coords[0]][coords[1]]) > 2) {
            if (coords[0] > Player.getX()) {
                clickOn(floor[Player.getX() + 1][Player.getY()]);
            }
            if (coords[0] < Player.getX()) {
                clickOn(floor[Player.getX() - 1][Player.getY()]);
            }
            if (coords[1] > Player.getY()) {
                clickOn(floor[Player.getX()][Player.getY() + 1]);
            }
            if (coords[1] < Player.getX()) {
                clickOn(floor[Player.getX()][Player.getY() - 1]);
            }
        }

        if (coords[0] == Player.getX() + 1) {
            while (Player.getHealth() != 0) {
                clickOn(floor[Player.getX()][Player.getY() - 1]);
                if (Player.getHealth() == 0) {
                    break;
                }
                clickOn(floor[Player.getX()][Player.getY() + 1]);
                if (Player.getHealth() == 0) {
                    break;
                }
            }
        } else if (coords[0] == Player.getX() - 1) {
            while (Player.getHealth() != 0) {
                clickOn(floor[Player.getX()][Player.getY() - 1]);
                if (Player.getHealth() == 0) {
                    break;
                }
                clickOn(floor[Player.getX()][Player.getY() + 1]);
                if (Player.getHealth() == 0) {
                    break;
                }
            }
        } else if (coords[1] == Player.getY() + 1) {
            while (Player.getHealth() != 0) {
                clickOn(floor[Player.getX() - 1][Player.getY()]);
                if (Player.getHealth() == 0) {
                    break;
                }
                clickOn(floor[Player.getX() + 1][Player.getY()]);
                if (Player.getHealth() == 0) {
                    break;
                }
            }
        } else if (coords[1] == Player.getY() - 1) {
            while (Player.getHealth() != 0) {
                clickOn(floor[Player.getX() - 1][Player.getY()]);
                if (Player.getHealth() == 0) {
                    break;
                }
                clickOn(floor[Player.getX() + 1][Player.getY()]);
                if (Player.getHealth() == 0) {
                    break;
                }
            }
        }
        assertEquals(Player.getHealth(), 0);
    }

    @Test
    public void firstRoomNoMonster() {
        ConfigView config = new ConfigView();
        ConfigController confcontr = new ConfigController(config);
        clickOn("Start").write("Name");
        clickOn("Enter your name!");
        clickOn("Ork Ship");
        clickOn("Tau Warrior");
        clickOn("Play!");
        Room curr = Map.getEntrance();
        assertTrue(curr.getNumMonsters() < 1);
    }

    @Test
    public void monsterDeathTest() {
        ConfigView config = new ConfigView();
        ConfigController confcontr = new ConfigController(config);
        clickOn("Start").write("Name");
        clickOn("Enter your name!");
        clickOn("Ork Ship");
        clickOn("Tau Warrior");
        clickOn("Play!");
        Room entrance = RoomController.getCurrentRoom();
        Tile[][] floor = RoomController.getCurrentRoom().getFloor();
        clickOn(floor[8][5]);
        clickOn(floor[10][5]);
        Room firstroom = RoomController.getCurrentRoom();
        floor = firstroom.getFloor();
        Monster[] mobs = firstroom.getMobs();
        Monster target = mobs[0];
        int[] coords = {target.getX(), target.getY()};
        while (firstroom.getDistance(floor[Player.getX()][Player.getY()],
                floor[coords[0]][coords[1]]) > 2) {
            if (coords[0] > Player.getX()) {
                clickOn(floor[Player.getX() + 1][Player.getY()]);
            }
            if (coords[0] < Player.getX()) {
                clickOn(floor[Player.getX() - 1][Player.getY()]);
            }
            if (coords[1] > Player.getY()) {
                clickOn(floor[Player.getX()][Player.getY() + 1]);
            }
            if (coords[1] < Player.getX()) {
                clickOn(floor[Player.getX()][Player.getY() - 1]);
            }
        }
        Player.attack(target);
        Player.attack(target);
        assertNull(mobs[0]);
    }

}
