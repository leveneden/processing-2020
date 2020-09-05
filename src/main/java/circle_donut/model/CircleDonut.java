package circle_donut.model;

import circle_donut.model.settings.CircleDonutSettings;
import common.stateful.Drawable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import processing.core.PApplet;

@Getter
@Setter
@AllArgsConstructor
public class CircleDonut implements Drawable {

    private CircleDonutSettings settings;
    private PApplet processing;

    @Override
    public void draw() {
        setUp();
        drawCircles();
        tearDown();
    }

    private void setUp() {
        processing.pushMatrix();
        processing.translate(settings.center.x, settings.center.y);
        processing.strokeWeight(settings.strokeWeight);
        processing.noFill();
    }

    private void drawCircles() {
        int circlesDrawn = 0;

        while (circlesDrawn < settings.numberOfCircles) {

            processing.stroke(calculateColorForCurrentCircle(circlesDrawn));

            if (circlesDrawn == 0 || circlesDrawn == settings.numberOfCircles) {
                drawCircleAtInitialPosition();
                rotate();
            } else {
                drawCircle();
                rotate();
            }

            circlesDrawn++;
        }
    }

    private void drawCircle() {
        processing.ellipse(0, (settings.centralNegativeSpaceDiameter/2) - (settings.circlesDiameter/2), settings.circlesDiameter, settings.circlesDiameter);
    }

    private int calculateColorForCurrentCircle(int circleIndex) {
        int r = Math.round(PApplet.map(circleIndex, 0, settings.numberOfCircles, processing.red(settings.colors[0]), processing.red(settings.colors[1])));
        int g = Math.round(PApplet.map(circleIndex, 0, settings.numberOfCircles, processing.green(settings.colors[0]), processing.green(settings.colors[1])));
        int b = Math.round(PApplet.map(circleIndex, 0, settings.numberOfCircles, processing.blue(settings.colors[0]), processing.blue(settings.colors[1])));

        return processing.color(r, g, b);
    }

    private void drawCircleAtInitialPosition() {
        tearDown();
        setUp();
        processing.stroke(calculateColorForCurrentCircle(settings.numberOfCircles));
        drawCircle();
    }

    private void tearDown() {
        processing.popMatrix();
    }

    private void rotate() {
        processing.rotate(PApplet.radians(360f/settings.numberOfCircles));
    }
}
