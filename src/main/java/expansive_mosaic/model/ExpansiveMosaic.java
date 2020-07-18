package expansive_mosaic.model;

import common.stateful.Drawable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

@Getter
@Setter
public class ExpansiveMosaic implements Drawable {

    private PImage image;
    private PVector center;
    private int tileWidth;
    private float expansionDistance;
    // distance mode (radial/square)
    private PApplet processing;

    public ExpansiveMosaic(String imagePath, PVector center, int tileWidth, float expansionDistance, PApplet processing) {
        this.image = processing.loadImage(imagePath);
        this.center = center;
        this.tileWidth = tileWidth;
        this.expansionDistance = expansionDistance;
        this.processing = processing;

        image.loadPixels();
    }

    // setters
    public void setImage(String imagePath) {
        this.image = processing.loadImage(imagePath);
    }

    @Override
    public void draw() {
        // draw central tile
        drawCentralTile();
        // draw tile rings (recursive)
            // get sample positions (discriminate distance mode)
            // get samples (tiles)
            // draw tiles at their position

    }

    private void drawCentralTile() {
        getTileAt(center).draw();
    }

    private Tile getTileAt(PVector location) {
        return new Tile(image, location, tileWidth, processing);
    }

}
