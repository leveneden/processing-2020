package criptographic_squares.view;

import common.model.Drawable;
import common.view.AbstractDrawer;
import criptographic_squares.model.Stick;
import processing.core.PApplet;

public class StickDrawer extends AbstractDrawer {

    public StickDrawer(PApplet processing) {
        super(processing);
    }

    @Override
    public void draw(Drawable drawable) {
        Stick stick = (Stick) drawable;
        if (stick.isPresent())
            processing.rect(stick.getPosition().x, stick.getPosition().y, stick.getWidth(), stick.getHeight());
    }
}
