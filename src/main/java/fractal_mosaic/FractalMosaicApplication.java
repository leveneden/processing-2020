package fractal_mosaic;

import fractal_mosaic.model.FractalMosaic;
import processing.core.PApplet;

public class FractalMosaicApplication extends PApplet {

    @Override
    public void settings() {
        size(596, 596);
    }

    @Override
    public void setup() {
    }

    @Override
    public void draw() {
        FractalMosaic mosaic = new FractalMosaic("src/3tfNVZHl_400x400.jpg"); // 3tfNVZHl_400x400
        mosaic.draw(this);
        noLoop();
        //System.exit(0);
    }

    public static void main(String... art) {
        PApplet.main("fractal_mosaic.FractalMosaicApplication");
    }
}
