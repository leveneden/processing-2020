package tracing_eyes;

import common.strategy.AbstractDrawingStrategy;
import lombok.SneakyThrows;
import processing.core.PApplet;
import processing.core.PVector;
import tracing_eyes.model.Eye;
import tracing_eyes.view.EyeDrawer;

public class TracingEyesApplication extends PApplet {

    private AbstractDrawingStrategy strategy;
    private EyeDrawer drawer;

    @Override
    public void settings() {
        size(512, 512);
    }

    @Override
    public void setup() {
        size(512, 512);
        drawer = new EyeDrawer(this);
    }

    @SneakyThrows
    @Override
    public void draw() {
        background(128);
        int eyeDiameter = 40;
        int pupilDiameter = 20;
        int separation= 10;
        for (int i = eyeDiameter; i < width - eyeDiameter / 2; i += eyeDiameter + separation) {
            for (int j = 0; j < height - eyeDiameter / 2; j += eyeDiameter + separation) {
                drawer.draw(new Eye(new PVector(i, j), eyeDiameter, pupilDiameter, new PVector(((float) mouseX), ((float) mouseY))));
            }
        }
        //noLoop();
        //System.exit(0);
    }

    public static void main(String... art) {
        PApplet.main("tracing_eyes.TracingEyesApplication");
    }
}
