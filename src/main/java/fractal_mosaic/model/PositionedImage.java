package fractal_mosaic.model;

import common.Drawable;
import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;

public class PositionedImage implements Drawable {

    private PImage image;
    private Point position;

    public PositionedImage(PImage image, Point position) {
        this.image = image;
        this.position = position;
    }

    @Override
    public void draw(PApplet processing) {
        processing.image(image, position.x, position.y);
    }
}
