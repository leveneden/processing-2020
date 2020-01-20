package criptographic_squares.view.impl;

import criptographic_squares.model.Drawable;
import criptographic_squares.model.Stick;
import criptographic_squares.model.StickFigure;
import criptographic_squares.view.AbstractDrawer;
import processing.core.PApplet;

public class StickFigureDrawer extends AbstractDrawer {

    private StickDrawer stickDrawer;

    public StickFigureDrawer(PApplet drawer) {
        super(drawer);
        stickDrawer = new StickDrawer(drawer);
    }

    @Override
    public void draw(Drawable drawable) {
        StickFigure stickFigure = (StickFigure) drawable;
        for (Stick stick : stickFigure.getSticks())
            stickDrawer.draw(stick);
    }
}
