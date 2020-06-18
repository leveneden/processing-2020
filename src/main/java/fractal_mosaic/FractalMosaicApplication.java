package fractal_mosaic;

import fractal_mosaic.model.FractalMosaic;
import processing.core.PApplet;

public class FractalMosaicApplication extends PApplet {

    @Override
    public void settings() {
        size(10716, 12294);
    }

    @Override
    public void setup() {
        background(149, 132, 116);
    }

    @Override
    public void draw() {
        FractalMosaic mosaic = new FractalMosaic("src/17795733_1832073207043982_8199114003662530719_n.png"); // 3tfNVZHl_400x400
        mosaic.draw(this);
        noLoop();
        save("whatsgoinon.png");
        System.exit(0);
    }

    public static void main(String... art) {
        PApplet.main("fractal_mosaic.FractalMosaicApplication");
    }
}
