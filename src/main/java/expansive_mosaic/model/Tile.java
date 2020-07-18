package expansive_mosaic.model;

import common.stateful.Drawable;
import lombok.AllArgsConstructor;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

@AllArgsConstructor
public class Tile implements Drawable {

    private PImage image;
    private PVector center;
    private int width;
    private PApplet processing;

    @Override
    public void draw() {

    }
}
