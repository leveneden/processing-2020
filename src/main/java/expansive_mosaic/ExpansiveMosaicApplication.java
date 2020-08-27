package expansive_mosaic;

import expansive_mosaic.model.ExpansiveMosaic;
import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;

public class ExpansiveMosaicApplication extends PApplet {

    @Override
    public void settings() {
        size(512, 512);
    }

    @Override
    public void setup() {
    }

    @Override
    public void draw() {
        // center 239, 244
        ExpansiveMosaic mosaic = new ExpansiveMosaic("src/main/resources/expansive_mosaic/input/FB_IMG_1567886152205.jpg",
                new Point(239, 244), new Point(width/2, height/2-70), 82,  50, this);
        mosaic.draw();

        noLoop();
        //System.exit(0);
    }

    public static void main(String... art) {
        PApplet.main("expansive_mosaic.ExpansiveMosaicApplication");
    }
}
