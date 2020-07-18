package expansive_mosaic.model;

import common.stateful.Drawable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import processing.core.PImage;
import processing.core.PVector;

@AllArgsConstructor
@Getter
public class TileFrame implements Drawable {

    private PImage image;
    private PVector location;
    private PVector samplingCenter;
    // distance mode
    // todo
    private float distanceFromSamplingCenter;
    int amountOfTiles;

    // getters
    private int getAmountOfTilesOnASingleSide() {
        return amountOfTiles/4 + 1;
    }

    @Override
    public void draw() {
        // drawUpperRowOfTiles();
        // drawSideColumnsOfTiles();
        // drawLowerRowOfTiles();
    }
}
