package rgb_transition.model;

import common.Drawable;
import lombok.Setter;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class RGBColorMapper implements Drawable {

    private List<Integer> colorGradient = new ArrayList<>();
    @Setter
    private int offsetIncrement = 1;
    @Setter
    private String imagePath;

    public RGBColorMapper(String imagePath) {
        this.imagePath = imagePath;
    }

    public RGBColorMapper(String imagePath, int offsetIncrement) {
        this.imagePath = imagePath;
        this.offsetIncrement = offsetIncrement;
    }

    @Override
    public void draw(PApplet processing) {

        createColorGradient(processing);

        drawImage(generateOutputImage(processing), processing);

        incrementOffset();
    }

    private void incrementOffset() {
        for (int i = 0; i < offsetIncrement; i++) {
            colorGradient.add(colorGradient.get(0));
            colorGradient.remove(0);
        }
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

    private PImage generateOutputImage(PApplet processing) {
        PImage inputImage = processing.loadImage(imagePath);
        inputImage.loadPixels();

        PImage outputImage = new PImage(inputImage.width, inputImage.height);
        outputImage.loadPixels();

        for (int i = 0; i < inputImage.pixels.length; i++) {
            int pixelColor = inputImage.pixels[i];

            int pixelColorMappedToColorGradient = mapPixelColorToColorGradient(pixelColor, processing);

            outputImage.pixels[i] = pixelColorMappedToColorGradient;
        }

        return outputImage;
    }

    private int mapPixelColorToColorGradient(int color, PApplet processing) {

            return colorGradient.get(Math.round(PApplet.map(
                    processing.brightness(color),
                    0,
                    255,
                    0,
                    getColorGradientMaxIndex()
            )));
    }

    private int getColorGradientMaxIndex() {
        return colorGradient.size() - 1;
    }

    private void drawImage(PImage image, PApplet processing) {
        processing.image(image, 0,0);
    }

}
