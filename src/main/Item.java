package main;

import javafx.scene.image.Image;

public abstract class Item {

    private Image sprite;
    private String type;

    public Image getSprite() {
        return sprite;
    }
    public String getType() {
        return type;
    }
}
