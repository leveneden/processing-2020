package expansive_mosaic.model;

import common.stateful.Drawable;
import lombok.AllArgsConstructor;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;

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
        return Math.round(samplingPoint.y - width/2);
    }

    private int getLowerEdge() {
        return Math.round(samplingPoint.y + width/2);
    }

    private int getRightEdge() {
        return Math.round(samplingPoint.x + width/2);
    }

    private int getLeftEdge() {
        return Math.round(samplingPoint.x - width/2);
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

        if (coordinatesAreWithinImageBounds(x, y, image)) {
            return image.pixels[x + y * image.width];
        } else {
            return EMPTY;
        }
    }

    // fixme: I'm unconfident about the upper limits here, future me
    private boolean coordinatesAreWithinImageBounds(int x, int y, PImage image) {
        return x < image.width && x >= 0 && y < image.height && y >= 0;
    }

}
