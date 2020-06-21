package rgb_transition;

import processing.core.PApplet;
import rgb_transition.layout.SingleFrameLayout;

public class RGBTransitionApplication extends PApplet {

    /*
        Rendered with:
        1. ffmpeg -f image2 -r 30 -i frame_%03d.png -vcodec libx264 -profile:v high444 -refs 16 -crf 0 -preset ultrafast render.mp4
        2. ffmpeg -i render.mp4 -pix_fmt yuv420p -vcodec libx264 -preset veryslow uploadable.mp4
     */

    @Override
    public void settings() {
        size(716, 610);
    }

    @Override
    public void setup() {
    }

    @Override
    public void draw() {
        new SingleFrameLayout().draw(this);
    }

    public static void main(String... art) {
        PApplet.main("rgb_transition.RGBTransitionApplication");
    }
}
