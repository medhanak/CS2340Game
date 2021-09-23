package tests.tests;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.*;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.verifyThat;

public class M6Test extends ApplicationTest {

    private JFXPanel panel = new JFXPanel();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage test1 = new Stage();
        Main welcome = new Main();
        welcome.start(test1);
    }

    @AfterEach
    public void tearDown() throws TimeoutException {
        FxToolkit.hideStage();
    }

    @Test
    public void testDeathScreen() {
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
        for (int i = 0; i < firstroom.getNumMonsters(); i++) {
            Monster monster = mobs[i];
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
        }
        assertNotNull(Main.getCurrentStage().getScene());
    }

    @Test
    public void testTyranidFinalRoom() {
        ConfigView config = new ConfigView();
        ConfigController confcontr = new ConfigController(config);
        clickOn("Start").write("Name");
        clickOn("Enter your name!");
        clickOn("Tyranid Hive");
        clickOn("Imperial Guard");
        clickOn("Play!");
        Room lastRoom = Map.getExit();
        assertEquals(lastRoom.getMobs().length, 3);
    }

    @Test
    public void testFinalBoss() {
        ConfigView config = new ConfigView();
        ConfigController confcontr = new ConfigController(config);
        clickOn("Start").write("Name");
        clickOn("Enter your name!");
        clickOn("Necron Hulk");
        clickOn("Imperial Guard");
        clickOn("Play!");
        Tile[][] floor = RoomController.getCurrentRoom().getFloor();
        clickOn(floor[6][5]);
        clickOn(floor[7][5]);
        clickOn(floor[8][5]);
        clickOn(floor[9][5]);
        clickOn(floor[10][5]);
        Room firstroom = RoomController.getCurrentRoom();
        Monster firstMonster = firstroom.getMobs()[0];
        Room lastRoom = Map.getExit();
        Monster finalBoss = lastRoom.getMobs()[0];

        assertNotEquals(firstMonster.getDamage(), finalBoss.getDamage());
    }

    @Test
    public void testOrkFinalRoom() {
        ConfigView config = new ConfigView();
        ConfigController confcontr = new ConfigController(config);
        clickOn("Start").write("Name");
        clickOn("Enter your name!");
        clickOn("Ork Ship");
        clickOn("Imperial Guard");
        clickOn("Play!");
        Room lastRoom = Map.getExit();
        assertEquals(lastRoom.getMobs().length, 1);
    }

    @Test
    public void testPlayAgain() {
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
        for (int i = 0; i < firstroom.getNumMonsters(); i++) {
            Monster monster = mobs[i];
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
        }
        clickOn("Yes").write("Name");
        clickOn("Enter your name!");
        clickOn("Ork Ship");
        clickOn("Imperial Guard");
        clickOn("Play!");
        assertNotNull(Map.getEntrance());
    }

    @Test
    public void testMoney() {
        ConfigView config = new ConfigView();
        ConfigController confcontr = new ConfigController(config);
        clickOn("Start").write("Name");
        clickOn("Enter your name!");
        clickOn("Ork Ship");
        clickOn("Imperial Guard");
        clickOn("Play!");
        Tile[][] floor = RoomController.getCurrentRoom().getFloor();
        clickOn(floor[6][5]);
        clickOn(floor[7][5]);
        clickOn(floor[8][5]);
        clickOn(floor[9][5]);
        clickOn(floor[10][5]);
        Room firstroom = RoomController.getCurrentRoom();
        floor = firstroom.getFloor();
        Monster[] mobs = firstroom.getMobs();
        for (int i = 0; i < firstroom.getNumMonsters(); i++) {
            Monster monster = mobs[i];
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

            Room curr = RoomController.getCurrentRoom();
            if (curr.getType().equals("shop")) {
                clickOn(floor[2][2]);
                break;
            }
        }
        assertNotEquals(Player.getMoney(), 500);
    }

    @Test
    public void testPurchaseItem() {
        ConfigView config = new ConfigView();
        ConfigController confcontr = new ConfigController(config);
        clickOn("Start").write("Name");
        clickOn("Enter your name!");
        clickOn("Ork Ship");
        clickOn("Imperial Guard");
        clickOn("Play!");
        Tile[][] floor = RoomController.getCurrentRoom().getFloor();
        clickOn(floor[6][5]);
        clickOn(floor[7][5]);
        clickOn(floor[8][5]);
        clickOn(floor[9][5]);
        clickOn(floor[10][5]);
        int itemCount = Inventory.getItemCount();
        Room firstroom = RoomController.getCurrentRoom();
        floor = firstroom.getFloor();
        Monster[] mobs = firstroom.getMobs();
        for (int i = 0; i < firstroom.getNumMonsters(); i++) {
            Monster monster = mobs[i];
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

            Room curr = RoomController.getCurrentRoom();
            if (curr.getType().equals("shop")) {
                clickOn(floor[2][2]);
                break;
            }
        }
        assertEquals(Inventory.getItemCount(), itemCount + 1);
    }

    @Test
    public void testMonsterMoves() {
        ConfigView config = new ConfigView();
        ConfigController confcontr = new ConfigController(config);
        clickOn("Start").write("Name");
        clickOn("Enter your name!");
        clickOn("Ork Ship");
        clickOn("Imperial Guard");
        clickOn("Play!");
        Tile[][] floor = RoomController.getCurrentRoom().getFloor();
        clickOn(floor[6][5]);
        clickOn(floor[7][5]);
        clickOn(floor[8][5]);
        clickOn(floor[9][5]);
        clickOn(floor[10][5]);
        Room firstroom = RoomController.getCurrentRoom();
        floor = firstroom.getFloor();
        Monster[] mobs = firstroom.getMobs();
        Monster mob = mobs[0];
        int mobx = mob.getX();
        int moby = mob.getY();
        clickOn(floor[6][5]);
        clickOn(floor[7][5]);
        assertTrue(mobx != mob.getX() || moby != mob.getY());
    }
}
