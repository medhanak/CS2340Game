package tests.tests;

import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import main.*;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

public class M5Test extends ApplicationTest {

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
    public void testStartInventory() {
        clickOn("Start").write("Name");
        clickOn("Enter your name!");
        clickOn("Ork Ship");
        clickOn("Imperial Guard");
        clickOn("Play!");

        assertEquals(1, Inventory.getItemCount());
        Inventory.useItem(0);
        assertEquals(Inventory.getArray()[0].getSprite(), Player.getWeapon().getSprite());
        assertEquals(0, Inventory.getItemCount());
    }

    @Test
    public void testAddToInventory() {
        clickOn("Start").write("Name");
        clickOn("Enter your name!");
        clickOn("Ork Ship");
        clickOn("Imperial Guard");
        clickOn("Play!");

        Inventory.addItem(new Potion("RedHealthPotion.png"));
        assertEquals(2, Inventory.getItemCount());
    }

    @Test
    public void testHealthPotion() {
        clickOn("Start").write("Name");
        clickOn("Enter your name!");
        clickOn("Ork Ship");
        clickOn("Tau Warrior");
        clickOn("Play!");
        Inventory.addItem(new Potion("RedHealthPotion.png"));
        Inventory.useItem(1);
        assertNotEquals(Player.getHealth(), 100);
    }

    @Test
    public void testAttackPotion() {
        clickOn("Start").write("Name");
        clickOn("Enter your name!");
        clickOn("Ork Ship");
        clickOn("Tau Warrior");
        clickOn("Play!");
        Inventory.addItem(new Potion("GreenAttackPotion.png"));
        int initDamage = Player.getStrength();
        Inventory.useItem(1);
        assertNotEquals(Player.getStrength(), initDamage);
    }

    @Test
    public void testSpeedPotion() {
        clickOn("Start").write("Name");
        clickOn("Enter your name!");
        clickOn("Ork Ship");
        clickOn("Tau Warrior");
        clickOn("Play!");
        Inventory.addItem(new Potion("YellowSpeedPotion.png"));
        int initSpeed = Player.getSpeed();
        Inventory.useItem(1);
        assertNotEquals(Player.getSpeed(), initSpeed);
    }

    @Test
    public void testDropItems() {
        clickOn("Start").write("Name");
        clickOn("Enter your name!");
        clickOn("Ork Ship");
        clickOn("Tau Warrior");
        clickOn("Play!");
        Inventory.useItem(0);
        Tile[][] floor = RoomController.getCurrentRoom().getFloor();
        clickOn(floor[8][5]);
        clickOn(floor[10][5]);
        Room firstroom = RoomController.getCurrentRoom();
        floor = firstroom.getFloor();
        Monster[] mobs = firstroom.getMobs();
        for (int i = 0; i < firstroom.getNumMonsters(); i++) {
            Monster target = mobs[i];
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
            while (target.getHP() > 0) {
                Player.attack(target);
            }
            clickOn(floor[coords[0]][coords[1]]);
        }
        assertNotEquals(Inventory.getItemCount(), 0);
    }

    @Test
    public void testNewWeapon() {
        clickOn("Start").write("Name");
        clickOn("Enter your name!");
        clickOn("Ork Ship");
        clickOn("Imperial Guard");
        clickOn("Play!");
        Weapon testweap = new Weapon("ReductorPistolWeapon.png");
        Inventory.addItem(testweap);
        Inventory.useItem(1);
        assertEquals(Player.getWeapon(), testweap);
    }

    @Test
    public void testUseWeapon() {
        clickOn("Start").write("Name");
        clickOn("Enter your name!");
        clickOn("Ork Ship");
        clickOn("Tau Warrior");
        clickOn("Play!");
        Inventory.addItem(new Weapon("PlasmaPistolWeapon.png"));
        Inventory.useItem(1);
        assertEquals(Player.getWeapon().getDamage(), 5);
    }
}
