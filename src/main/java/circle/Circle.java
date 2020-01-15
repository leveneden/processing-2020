package circle;

import processing.core.PApplet;

import java.awt.*;

public class Circle extends PApplet {

    public void settings() {
        size(1024, 1024);

    }

    public void setup () {
        background(0);
        strokeWeight(1);
        stroke(255);
    }

    public void draw() {
        for (int i = 0; i <= 360; i += 10) {

            Point current = new Point(
                    width / 2 + (Math.round(cos(radians(i)) * 300)),
                    height / 2 + (Math.round(sin(radians(i)) * 300))
            );

            drawShape(current.x, current.y);
        }
        stroke( 0);
        strokeWeight(100);
        fill(0);
        Point previous = null;
        for (int i = 0; i <= 360; i += 10) {

            Point current = new Point(
                    width / 2 + (Math.round(cos(radians(i)) * 300)),
                    height / 2 + (Math.round(sin(radians(i)) * 300))
            );

            if (previous == null) previous = new Point(current.x, current.y);

            line(previous.x, previous.y, current.x, current.y);

            previous = new Point(current.x, current.y);
        }

        save("/src/draft2.png");


        noLoop();

    }

    public static void main(String[] passedArgs) {
        String[] appletArgs = new String[]{"circle.Circle"};
        PApplet.main(appletArgs);
    }

    public void drawShape(int x, int y) {
        Point previous = null;
        for (int i = 0; i <= 360; i += 10) {

            Point current = new Point(
                    x + (Math.round(cos(radians(i)) * 50)),
                    y + (Math.round(sin(radians(i)) * 50))
            );

            if (previous == null) previous = new Point(current.x, current.y);

            //line(previous.x, previous.y, current.x, current.y);

            if (current.x > x) line(current.x, current.y, current.x + 50, current.y);
            if (current.x < x) line(current.x, current.y, current.x - 50, current.y);
            if (current.y > y) line(current.x, current.y, current.x, current.y + 50);
            if (current.y < y) line(current.x, current.y, current.x, current.y - 50);

            previous = new Point(current.x, current.y);


        }
    }
}

