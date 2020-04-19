package fractal_mosaic.model;

import common.Drawable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import processing.core.PApplet;
import processing.core.PImage;

import java.awt.Point;

@AllArgsConstructor
@Getter
@Setter
public class PositionedImage implements Drawable {

    private PImage image;
    private Point position;

    @Override
    public void draw(PApplet processing) {
        processing.image(image, position.x, position.y);
    }
}
