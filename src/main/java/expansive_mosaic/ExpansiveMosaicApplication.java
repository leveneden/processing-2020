package expansive_mosaic;

import expansive_mosaic.model.ExpansiveMosaic;
import expansive_mosaic.model.constant.DistanceMode;
import processing.core.PApplet;

import java.awt.Point;

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
        // width 82
        // 40 : 12
        background(255);
        int tileWidth =  40;
        int expansionDistance = 12;

        ExpansiveMosaic mosaic = new ExpansiveMosaic("src/main/resources/expansive_mosaic/input/FB_IMG_1567886152205.jpg",
                new Point(239, 244), new Point(width/2, height/2), tileWidth,  expansionDistance, 12, DistanceMode.SQUARE, this);
        mosaic.draw();

        save(String.format("src/main/resources/expansive_mosaic/output/tile width %d. expansion distance %d. %dx%d.png", tileWidth, expansionDistance, width, height));

        noLoop();
        System.exit(0);
    }

    public static void main(String... art) {
        PApplet.main("expansive_mosaic.ExpansiveMosaicApplication");
    }
}
