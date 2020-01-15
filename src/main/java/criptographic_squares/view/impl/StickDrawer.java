package criptographic_squares.view.impl;

import criptographic_squares.model.Drawable;
import criptographic_squares.view.AbstractDrawer;
import processing.core.PApplet;

public class StickDrawer extends AbstractDrawer {

    public StickDrawer(PApplet drawer) {
        super(drawer);
    }

    @Override
    public void draw(Drawable drawable) {
        this.drawer.rect(0,0,0,0);
        // todo: concrete implementation of the draw method
    }
}
