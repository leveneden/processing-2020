package rgb_transition.layout;

import common.Drawable;
import processing.core.PApplet;
import processing.core.PImage;
import rgb_transition.model.RGBColorMapper;

public class SingleFrameLayout implements Drawable {

    private int frameCount = 0;

    @Override
    public void draw(PApplet processing) {

        RGBColorMapper colorMapper = new RGBColorMapper("src/main/resources/rgb-transition/input/input.png", 14);

        while (frameCount <= 300) {
            colorMapper.draw(processing);
            drawWatermark(processing);
            saveImage(processing);
            frameCount++;
        }
        System.exit(0);
    }

    private void saveImage(PApplet processing) {
        processing.save(String.format("src/main/resources/rgb-transition/output/render/frame_%03d.png", frameCount));
    }

    private void drawWatermark(PApplet processing) {
        PImage watermark = processing.loadImage("src/main/resources/rgb-transition/input/watermark.png");
        processing.image(watermark, 0, 0);
    }
}
