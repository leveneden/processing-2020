package expansive_mosaic.model;

import common.stateful.Drawable;
import expansive_mosaic.model.constant.DistanceMode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class TileFrame implements Drawable {

    private PImage image;
    private Point location;
    private int tileWidth;
    private Point samplingCenter;
    private float distanceFromSamplingCenter;
    private int amountOfTiles;
    private DistanceMode distanceMode;
    private PApplet processing;

    private int getAmountOfTilesOnASingleSide() {
        return amountOfTiles/4 + 1;
    }

    private int getTileIndexAtTheTopCenterOFTheFrame() {
        return amountOfTiles/8;
    }

    private float getDistanceForEachStepOfMovementWhenSamplingPointsInASquare() {
        return distanceFromSamplingCenter / (amountOfTiles/8);
    }

    @Override
    public void draw() {
        final List<Point> tileLocations = getTileLocations();
        final Point[] samplingPoints = getSamplingPoints();

        for (int i = 0; i < amountOfTiles; i++) {
            new Tile(image, tileLocations.get(i), samplingPoints[i], tileWidth, processing).draw();
        }
    }

    /**
     * returns tile locations clockwise from the upper left corner
     *
     * @return List of tile locations
     */
    private List<Point> getTileLocations() {
        List<Point> tileLocations = new ArrayList<>();

        Point currentLocation = new Point(location.x, location.y);

        // upper row
        for (int i = 0; i <getAmountOfTilesOnASingleSide(); i++) {

            if (i != 0) {
                currentLocation.x += tileWidth;
            }
            tileLocations.add((Point) currentLocation.clone());
        }

        // right column
        for (int i = 0; i < getAmountOfTilesOnASingleSide() - 1; i++) {

            currentLocation.y += tileWidth;
            tileLocations.add((Point) currentLocation.clone());
        }

        // bottom row
        for (int i = 0; i < getAmountOfTilesOnASingleSide() - 1; i++) {

            currentLocation.x -= tileWidth;
            tileLocations.add((Point) currentLocation.clone());
        }

        // left column
        for (int i = 0; i < getAmountOfTilesOnASingleSide() - 2; i++) {

            currentLocation.y -= tileWidth;
            tileLocations.add((Point) currentLocation.clone());
        }

        assert tileLocations.size() == amountOfTiles;

        return tileLocations;
    }

    private Point[] getSamplingPoints() {

        switch (distanceMode) {
            case RADIAL:
                return getSamplingPointsInARadius();
            case SQUARE:
                return getSamplingPointsInASquare();
        }
        return null;
    }

    private Point[] getSamplingPointsInARadius() {
        Point[] samplingPoints = new Point[amountOfTiles];

        int pointsStored = 0;
        for (float degrees = 180; degrees > 180 - 360; degrees -= 360f/amountOfTiles) {
            int indexToStore = (pointsStored + getTileIndexAtTheTopCenterOFTheFrame()) % amountOfTiles;

            float x = samplingCenter.x + PApplet.sin(PApplet.radians(degrees)) * distanceFromSamplingCenter;
            float y = samplingCenter.y + PApplet.cos(PApplet.radians(degrees)) * distanceFromSamplingCenter;

            if (pointsStored < amountOfTiles) {
                samplingPoints[indexToStore] = new Point(Math.round(x), Math.round(y));
            }
            pointsStored++;
        }

        return samplingPoints;
    }

    private Point[] getSamplingPointsInASquare() {

        Point[] samplingPoints = new Point[amountOfTiles];

        PVector currentLocation = new PVector(
                samplingCenter.x - distanceFromSamplingCenter,
                samplingCenter.y -distanceFromSamplingCenter
        );

        int totalLocationsSampled = 0;
        final float distanceForEachStep = getDistanceForEachStepOfMovementWhenSamplingPointsInASquare();

        // upper row
        for (int i = 0; i <getAmountOfTilesOnASingleSide(); i++) {

            if (i != 0) {
                currentLocation.x += distanceForEachStep;
            }
            samplingPoints[totalLocationsSampled] = toPoint(currentLocation);
            totalLocationsSampled++;
        }

        // right column
        for (int i = 0; i < getAmountOfTilesOnASingleSide() - 1; i++) {

            currentLocation.y += distanceForEachStep;
            samplingPoints[totalLocationsSampled] = toPoint(currentLocation);
            totalLocationsSampled++;
        }

        // bottom row
        for (int i = 0; i < getAmountOfTilesOnASingleSide() - 1; i++) {

            currentLocation.x -= distanceForEachStep;
            samplingPoints[totalLocationsSampled] = toPoint(currentLocation);
            totalLocationsSampled++;
        }

        // left column
        for (int i = 0; i < getAmountOfTilesOnASingleSide() - 2; i++) {

            currentLocation.y -= distanceForEachStep;
            samplingPoints[totalLocationsSampled] = toPoint(currentLocation);
            totalLocationsSampled++;
        }

        assert totalLocationsSampled == amountOfTiles;

        return samplingPoints;
    }

    private Point toPoint(PVector vector) {
        return new Point(Math.round(vector.x), Math.round(vector.y));
    }

}
