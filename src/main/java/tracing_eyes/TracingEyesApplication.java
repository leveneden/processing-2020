package tracing_eyes;

import processing.core.PApplet;
import tracing_eyes.layout.GridOfRealisticEyesLookingAtMovingCircleLayout;

public class TracingEyesApplication extends PApplet {

    /*
        Rendered with:
        1. ffmpeg -f image2 -r 30 -i frame_%03d.png -vcodec libx264 -profile:v high444 -refs 16 -crf 0 -preset ultrafast render.mp4
        2. ffmpeg -i render.mp4 -pix_fmt yuv420p -vcodec libx264 -preset veryslow uploadable.mp4
     */

    @Override
    public void settings() {
        size(720, 540); // medium
        // size(2048, 1536); // big
    }

    @Override
    public void setup() {
    }

    @Override
    public void draw() {
        new GridOfRealisticEyesLookingAtMovingCircleLayout().draw(this);
        noLoop();
        System.exit(0);
    }

    public static void main(String... art) {
        PApplet.main("tracing_eyes.TracingEyesApplication");
    }
}
