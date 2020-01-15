package criptographic_squares.view;

import criptographic_squares.model.Drawable;
import lombok.AllArgsConstructor;
import processing.core.PApplet;

@AllArgsConstructor
public abstract class AbstractDrawer implements Drawer {

    protected PApplet drawer;

    @Override
    public abstract void draw(Drawable drawable);
}
