package expansive_mosaic.model;

import common.stateful.Drawable;
import expansive_mosaic.model.constant.DistanceMode;
import lombok.Getter;
import lombok.Setter;
import processing.core.PApplet;
import processing.core.PImage;

import java.awt.Point;

@Getter
@Setter
public class ExpansiveMosaic implements Drawable {

    private PImage image;
    private Point samplingCenter;
    private Point drawingCenter;
    private int tileWidth;
    private float expansionDistance;
    private int numberOfFrames;
    private DistanceMode distanceMode;
    private PApplet processing;

    public ExpansiveMosaic(String imagePath, Point samplingCenter, Point drawingCenter, int tileWidth, float expansionDistance, int numberOfFrames, DistanceMode distanceMode, PApplet processing) {
        this.image = processing.loadImage(imagePath);
        this.samplingCenter = samplingCenter;
        this.drawingCenter = drawingCenter;
        this.tileWidth = tileWidth;
        this.expansionDistance = expansionDistance;
        this.numberOfFrames = numberOfFrames;
        this.distanceMode = distanceMode;
        this.processing = processing;

        image.loadPixels();
    }

    // setters
    public void setImage(String imagePath) {
        this.image = processing.loadImage(imagePath);
    }

    @Override
    public void draw() {
        drawCentralTile();
        drawAllAdjacentTiles();
    }

    private void drawCentralTile() {
        Point location = new Point(drawingCenter.x - tileWidth/2, drawingCenter.y - tileWidth/2);
        getTileAt(location, samplingCenter).draw();
    }

    private Tile getTileAt(Point location, Point samplingPoint) {
        return new Tile(image, location, samplingPoint, tileWidth, processing);
    }

    private void drawAllAdjacentTiles() {
        Point firstTileFrameLocation = new Point(
                Math.round(drawingCenter.x - tileWidth * 1.5f),
                Math.round(drawingCenter.y - tileWidth * 1.5f)
        );
        TileFrame firstTileFrame = new TileFrame(image, firstTileFrameLocation, tileWidth, samplingCenter, expansionDistance, 8, distanceMode, processing);
        firstTileFrame.draw();
        drawNextTileFrameRecursively(firstTileFrame, numberOfFrames);
    }

    private void drawNextTileFrameRecursively(TileFrame previousTileFrame, int remainingSteps) {
        if (remainingSteps > 0) {
            Point currentLocation = new Point(
                    previousTileFrame.getLocation().x - previousTileFrame.getTileWidth(),
                    previousTileFrame.getLocation().y - previousTileFrame.getTileWidth()
                    );
            TileFrame currentFrame = new TileFrame(image, currentLocation, previousTileFrame.getTileWidth(), previousTileFrame.getSamplingCenter(), previousTileFrame.getDistanceFromSamplingCenter() + expansionDistance, previousTileFrame.getAmountOfTiles() + 8,previousTileFrame.getDistanceMode(), processing);
            currentFrame.draw();

            drawNextTileFrameRecursively(currentFrame, remainingSteps - 1);
        }

    }

}
