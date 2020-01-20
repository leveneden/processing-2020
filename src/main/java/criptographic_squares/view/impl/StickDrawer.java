package criptographic_squares.view.impl;

import criptographic_squares.model.Drawable;
import criptographic_squares.model.Stick;
import criptographic_squares.view.AbstractDrawer;
import processing.core.PApplet;

public class StickDrawer extends AbstractDrawer {

    public StickDrawer(PApplet drawer) {
        super(drawer);
    }

    @Override
    public void draw(Drawable drawable) {
        Stick stick = (Stick) drawable;
        if (stick.isPresent())
            drawer.rect(stick.getPosition().x, stick.getPosition().y, stick.getWidth(), stick.getHeight());
    }
}
