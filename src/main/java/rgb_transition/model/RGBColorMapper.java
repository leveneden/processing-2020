package rgb_transition.model;

import common.Drawable;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class RGBColorMapper implements Drawable {

    private static List<Integer> colorGradient = new ArrayList<>();

    @Override
    public void draw(PApplet processing) {
        createColorGradient(processing);
    }

    private void createColorGradient(PApplet processing) {
        if (colorGradient.isEmpty()) {
            PImage gradientImage = processing.loadImage("src/main/resources/rgb-transition/rgb-gradient.png");
            gradientImage.loadPixels();

            for (int i = 0; i < gradientImage.width; i++) {
                if (!colorGradient.contains(gradientImage.pixels[i])) {
                    colorGradient.add(gradientImage.pixels[i]);
                }
            }
        }
    }

    public List<Integer> getColorGradient() {
        return colorGradient;
    }
}
