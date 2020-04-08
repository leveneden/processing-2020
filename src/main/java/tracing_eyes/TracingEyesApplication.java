package tracing_eyes;

import lombok.SneakyThrows;
import processing.core.PApplet;
import processing.core.PVector;
import tracing_eyes.model.Eye;

public class TracingEyesApplication extends PApplet {

    @Override
    public void settings() {
        size(256, 192);
    }

    @Override
    public void setup() {
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
                new Eye(new PVector(i, j), eyeDiameter, pupilDiameter, new PVector(((float) mouseX), ((float) mouseY)))
                .draw(this);
            }
        }
        // noLoop();
        // System.exit(0);
    }

    public static void main(String... art) {
        PApplet.main("tracing_eyes.TracingEyesApplication");
    }
}
