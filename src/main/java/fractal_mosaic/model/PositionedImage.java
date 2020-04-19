package fractal_mosaic.model;

import common.Drawable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import processing.core.PApplet;
import processing.core.PImage;

import java.awt.Point;

@AllArgsConstructor
public class PositionedImage implements Drawable {

    private PImage image;
    @Getter @Setter
    private Point position;

    public PositionedImage(PImage image) {
        this.image = image;
    }

    @Override
    public void draw(PApplet processing) {
        processing.image(image, position.x, position.y);
    }

    @Override
    protected Object clone() {
        return new PositionedImage(image.copy(), new Point(position.x, position.y));
    }

    public PositionedImage copy() {
        return (PositionedImage) clone();
    }

    // getters
    public PImage get() {
        return this.image;
    }

    public int getWidth() {
        return this.image.width;
    }

    public int getHeight() {
        return this.image.height;
    }

    public int getX() {
        return this.position.x;
    }

    public int getY() {
        return this.position.y;
    }

    // seters
    public void setX(int x) {
        this.position.x = x;
    }

    public void setY(int y) {
        this.position.y = y;
    }
}
