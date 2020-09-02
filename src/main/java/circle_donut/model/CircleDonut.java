package circle_donut.model;

import common.stateful.Drawable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import processing.core.PApplet;

import java.awt.Point;

@Getter
@Setter
@AllArgsConstructor
public class CircleDonut implements Drawable {

    private Point center;
    private int centralNegativeSpaceDiameter;
    private int numberOfCircles;
    private int circlesDiameter;
    private int strokeWeight;
    private int[] colors;
    private PApplet processing;

    @Override
    public void draw() {
        setUp();

        drawCircles();

        tearDown();
    }

    private void setUp() {
        processing.pushMatrix();
        processing.translate(center.x, center.y);
        processing.strokeWeight(strokeWeight);
        processing.noFill();
    }

    private void drawCircles() {
        int circlesDrawn = 0;

        while (circlesDrawn < numberOfCircles) {
            processing.stroke(calculateColorForCurrentCircle(circlesDrawn));
            drawCircle();
            processing.rotate(PApplet.radians(360f/numberOfCircles));

            circlesDrawn++;
        }

        tearDown();
        setUp();

        processing.stroke(calculateColorForCurrentCircle(circlesDrawn));
        drawCircle();
    }

    private void drawCircle() {
        processing.ellipse(-(centralNegativeSpaceDiameter/2)+(circlesDiameter/2), 0, circlesDiameter, circlesDiameter);
    }

    private int calculateColorForCurrentCircle(int circleIndex) {
        int r = Math.round(PApplet.map(circleIndex, 0, numberOfCircles, processing.red(colors[0]), processing.red(colors[1])));
        int g = Math.round(PApplet.map(circleIndex, 0, numberOfCircles, processing.green(colors[0]), processing.green(colors[1])));
        int b = Math.round(PApplet.map(circleIndex, 0, numberOfCircles, processing.blue(colors[0]), processing.blue(colors[1])));

        return processing.color(r, g, b);
    }

    private void tearDown() {
        processing.popMatrix();
    }
}
