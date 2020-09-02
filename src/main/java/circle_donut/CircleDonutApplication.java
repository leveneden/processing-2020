package circle_donut;

import circle_donut.model.CircleDonut;
import processing.core.PApplet;

import java.awt.*;

public class CircleDonutApplication extends PApplet {

    @Override
    public void settings() {
        size(1024, 1024);
    }

    @Override
    public void setup() {
        background(20);
    }

    @Override
    public void draw() {

        Point center = new Point(width/2, height/2);

        CircleDonut donut = new CircleDonut(center, 88, 200, 440, 1, new int[] {color(75, 184, 75), color(42, 195, 203)}, this);
        donut.draw();

        save("src/main/resources/circle_donut/output.png");

        noLoop();
        System.exit(0);
    }

    public static void main(String... art) {
        PApplet.main("circle_donut.CircleDonutApplication");
    }
}
