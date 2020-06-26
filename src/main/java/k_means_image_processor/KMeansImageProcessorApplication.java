package k_means_image_processor;

import processing.core.PApplet;

public class KMeansImageProcessorApplication extends PApplet {

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
        //System.exit(0);
    }

    public static void main(String... art) {
        PApplet.main("k_means_image_processor.KMeansImageProcessorApplication");
    }
}