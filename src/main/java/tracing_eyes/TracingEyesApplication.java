package tracing_eyes;

import common.strategy.AbstractDrawingStrategy;
import processing.core.PApplet;

public class TracingEyesApplication extends PApplet {

    private AbstractDrawingStrategy strategy;

    @Override
    public void settings() {
        size(512, 512);
    }

    @Override
    public void setup() {

    }

    @Override
    public void draw() {
        noLoop();
        System.exit(0);
    }

    public static void main(String... art) {
        PApplet.main("tracing_eyes.TracingEyesApplication");
    }
}
