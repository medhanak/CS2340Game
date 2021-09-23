//package tests;
//
//import javafx.embed.swing.JFXPanel;
//import javafx.stage.Stage;
//import main.*;
//import org.junit.Test;
//import org.testfx.framework.junit.ApplicationTest;
//import org.testfx.matcher.base.NodeMatchers;
//
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.testfx.api.FxAssert.verifyThat;
//
//public class M3Test extends ApplicationTest {
//
//    private JFXPanel panel = new JFXPanel();
//    private Room[][] map1;
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        Stage test1 = new Stage();
//        Main welcome = new Main();
//        welcome.start(test1);
//    }
//
//    @Test
//    public void testRoomCount() {
//        ConfigView test = new ConfigView();
//        ConfigController imperial = new ConfigController(test);
//        clickOn("Start").write("Name");
//        clickOn("Enter your name!");
//        clickOn("Ork Ship");
//        clickOn("Imperial Guard");
//        clickOn("Play!");
//        int count = 0;
//        Room[][] themap = Map.getMap();
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                if (themap[i][j].getOn()) {
//                    count++;
//                }
//            }
//        }
//        assertTrue(count >= 8);
//    }
//
//    @Test
//    public void testGameScreen() {
//        ConfigView test = new ConfigView();
//        ConfigController imperial = new ConfigController(test);
//        clickOn("Start").write("Name");
//        clickOn("Enter your name!");
//        clickOn("Ork Ship");
//        clickOn("Imperial Guard");
//        clickOn("Play!");
//        verifyThat("Exit", NodeMatchers.isNotNull());
//    }
//
//    @Test
//    public void testNewMaps() {
//        ConfigView testPlay = new ConfigView();
//        ConfigController imperial = new ConfigController(testPlay);
//        clickOn("Start").write("Name");
//        clickOn("Enter your name!");
//        clickOn("Ork Ship");
//        clickOn("Imperial Guard");
//        clickOn("Play!");
//
//        map1 = Map.getMap();
//        assertNotNull(map1);
//    }
//
//    @Test
//    public void testRandomMaps() {
//        ConfigView testPlay = new ConfigView();
//        ConfigController imperial = new ConfigController(testPlay);
//        clickOn("Start").write("Name");
//        clickOn("Enter your name!");
//        clickOn("Ork Ship");
//        clickOn("Imperial Guard");
//        clickOn("Play!");
//        Room[][] map2 = Map.getMap();
//
//        assertNotEquals(map2, map1);
//    }
//
//    @Test
//    public void testExits() {
//        ConfigView testPlay = new ConfigView();
//        ConfigController imperial = new ConfigController(testPlay);
//        clickOn("Start").write("Name");
//        clickOn("Enter your name!");
//        clickOn("Ork Ship");
//        clickOn("Imperial Guard");
//        clickOn("Play!");
//        clickOn("Exit");
//        verifyThat("Exit", NodeMatchers.isNotNull());
//    }
//
//    @Test
//    public void testEntranceExits() {
//        ConfigView testPlay = new ConfigView();
//        ConfigController imperial = new ConfigController(testPlay);
//        clickOn("Start").write("Name");
//        clickOn("Enter your name!");
//        clickOn("Ork Ship");
//        clickOn("Imperial Guard");
//        clickOn("Play!");
//        Room enter = Map.getEntrance();
//        if (enter.hasBottomExit()) {
//            assertNotNull(enter.getBottomRoom());
//        }
//        if (enter.hasLeftExit()) {
//            assertNotNull(enter.getLeftRoom());
//        }
//        if (enter.hasTopExit()) {
//            assertNotNull(enter.getTopRoom());
//        }
//        if (enter.hasRightExit()) {
//            assertNotNull(enter.getRightRoom());
//        }
//    }
//
//    @Test
//    public void testRoomChange() {
//        ConfigView testPlay = new ConfigView();
//        ConfigController imperial = new ConfigController(testPlay);
//        clickOn("Start").write("Name");
//        clickOn("Enter your name!");
//        clickOn("Ork Ship");
//        clickOn("Imperial Guard");
//        clickOn("Play!");
//        Room entrance = Map.getEntrance();
//
//        clickOn("Exit");
//
//        Room currRoom = Map.getRoom();
//
//        assertNotEquals(currRoom, entrance);
//    }
//
//    @Test
//    public void testMapView() {
//        ConfigView testPlay = new ConfigView();
//        ConfigController imperial = new ConfigController(testPlay);
//        clickOn("Start").write("Name");
//        clickOn("Enter your name!");
//        clickOn("Ork Ship");
//        clickOn("Imperial Guard");
//        clickOn("Play!");
//        clickOn("Map");
//
//        verifyThat("Room 0", NodeMatchers.isNotNull());
//    }
//}
