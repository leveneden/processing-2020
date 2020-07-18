package expansive_mosaic.model;

import common.stateful.Drawable;
import lombok.AllArgsConstructor;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;

@AllArgsConstructor
public class Tile implements Drawable {

    private PImage image;
    private Point location;
    private PVector samplingPoint;
    private int width;
    private PApplet processing;
    private PImage tileContent;
    private boolean hasContent;

    private static final int EMPTY = -16776961; // #0000FF

    public Tile(PImage image, Point location, PVector samplingPoint, int width, PApplet processing) {
        this.image = image;
        this.location = location;
        this.samplingPoint = samplingPoint;
        this.width = width;
        this.processing = processing;

        fillTileContent();
        hasContent = hasSampledPixels();
    }

    private boolean hasSampledPixels() {
        for (int color: tileContent.pixels) {
            if (color != EMPTY) {
                return true;
            }
        }
        return false;
    }

    public boolean hasContent() {
        return this.hasContent;
    }

    @Override
    public void draw() {
        processing.image(tileContent, location.x, location.y);
    }

    private int getUpperEdge() {
        return location.y;
    }

    private int getLowerEdge() {
        return location.y + width;
    }

    private int getRightEdge() {
        return location.x + width;
    }

    private int getLeftEdge() {
        return location.x;
    }

    private void fillTileContent() {

        tileContent = new PImage(width, width);
        tileContent.loadPixels();

        int currentPixelIndex = 0;

        for (int y = getUpperEdge(); y < getLowerEdge(); y++) {
            for (int x = getLeftEdge(); x < getRightEdge(); x++) {
                tileContent.pixels[currentPixelIndex] = getColorAt(x, y, image);
                currentPixelIndex++;
            }
        }
    }

    private int getColorAt(int x, int y, PImage image) {
        image.loadPixels();
        try {
            return image.pixels[x + y * image.width];
        } catch (IndexOutOfBoundsException ex) {
            return EMPTY;
        }
    }

}
