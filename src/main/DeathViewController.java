package main;

public class DeathViewController {

    private DeathView view;

    public DeathViewController(DeathView pane) {
        this.view = pane;
    }

    public void goToConfig() {
        Main.changeView(new ConfigView());
    }
}
