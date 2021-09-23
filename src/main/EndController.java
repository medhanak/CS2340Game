package main;


public class EndController {

    private EndView view;

    public EndController(EndView view) {
        this.view = view;
    }

    public void goToConfig() {
        Main.changeView(new ConfigView());
    }
}
