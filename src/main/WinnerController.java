package main;

public class WinnerController {
    private WinnerView view;

    public WinnerController(WinnerView pane) {
        this.view = pane;
    }

    public void goToConfig() {
        Main.changeView(new ConfigView());
    }

}
