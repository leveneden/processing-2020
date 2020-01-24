package criptographic_squares.view;

import common.model.Drawable;
import criptographic_squares.model.Stick;
import criptographic_squares.model.StickFigure;
import common.view.AbstractDrawer;
import processing.core.PApplet;

public class StickFigureDrawer extends AbstractDrawer {

    private StickDrawer stickDrawer;

    public StickFigureDrawer(PApplet processing) {
        super(processing);
        stickDrawer = new StickDrawer(processing);
    }

    @Override
    public void draw(Drawable drawable) {
        StickFigure stickFigure = (StickFigure) drawable;
        for (Stick stick : stickFigure.getSticks())
            stickDrawer.draw(stick);
    }
}
