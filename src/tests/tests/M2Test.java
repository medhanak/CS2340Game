package tests;

import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import main.ConfigController;
import main.ConfigView;
import main.Main;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;

public class M2Test extends ApplicationTest {

    private JFXPanel panel = new JFXPanel();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage test1 = new Stage();
        Main welcome = new Main();
        welcome.start(test1);
    }

    // Abby JUnit Tests
    @Test
    public void testStart() {
        clickOn("Start");
        verifyThat("Group54", NodeMatchers.isNotNull());
    }

    @Test
    public void testHowToPlay() {
        clickOn("How to Play");
        verifyThat("Navigate through the spaceship and defeat all the enemies!",
                NodeMatchers.isNotNull());
    }
    
    // Thomas JUnit Tests
    @Test
    public void testNameInput() {
        clickOn("Start").write("Generic Player Name");
        verifyThat("Generic Player Name", NodeMatchers.isNotNull());
    }

    @Test
    public void testPlay() {
        clickOn("Start").write("Generic Player Name");
        clickOn("Enter your name!");
        clickOn("Ork Ship");
        clickOn("Imperial Guard");
        clickOn("Play!");
        verifyThat("EXIT", NodeMatchers.isNotNull());
    }

    // Maridel JUnit Tests
    @Test
    public void testImperialWeapon() {
        ConfigView test = new ConfigView();
        ConfigController imperial = new ConfigController(test);
        clickOn("Start").write("Generic Player Name");
        clickOn("Enter your name!");
        clickOn("Ork Ship");
        clickOn("Imperial Guard");
        clickOn("Play!");
        assertEquals("Lasgun", imperial.getPlayer1().getWeapon().getType());
    }

    @Test
    public void testTauHealth() {
        ConfigView test = new ConfigView();
        ConfigController tau = new ConfigController(test);
        tau.updateInfo("Tau");
        assertEquals(100, tau.getPlayer1().getHealth());
    }

    // Medhana JUnit Tests
    @Test
    public void testMarineMoney() {
        ConfigView test = new ConfigView();
        ConfigController marine = new ConfigController(test);
        clickOn("Start").write("Generic Player Name");
        clickOn("Enter your name!");
        clickOn("Ork Ship");
        clickOn("Space Marine");
        clickOn("Play!");
        assertEquals(100, marine.getPlayer1().getMoney());
    }

    @Test
    public void testEldarLives() {
        ConfigView test = new ConfigView();
        ConfigController eldar = new ConfigController(test);
        clickOn("Start").write("Generic Player Name");
        clickOn("Enter your name!");
        clickOn("Ork Ship");
        clickOn("Eldar Wizard");
        clickOn("Play!");
        assertEquals(1, eldar.getPlayer1().getLives());
    }
}
