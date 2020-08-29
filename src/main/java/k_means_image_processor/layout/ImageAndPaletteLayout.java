package k_means_image_processor.layout;

import common.stateless.Drawable;
import k_means_image_processor.model.Cluster;
import k_means_image_processor.model.KMeansResult;
import lombok.AllArgsConstructor;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ImageAndPaletteLayout implements Drawable {

    private KMeansResult kMeansResult;
    private String imagePath;

    @Override
    public void draw(PApplet processing) {
        drawImage(imagePath, processing);
        drawPalette(getColors(processing),100, processing);
    }

    private void drawImage(String imagePath, PApplet processing) {
        PImage image = processing.loadImage(imagePath);
        processing.image(image, 0, 0);
    }

    private void drawPalette(List<Integer> colors, int paleteHeight, PApplet processing) {
        processing.noStroke();
        int portionOfWidth = processing.width / colors.size();
        for (int i = 0; i < colors.size(); i ++) {
            processing.fill(colors.get(i));
            processing.rect(i * portionOfWidth, processing.height - paleteHeight, processing.width, paleteHeight);
        }
    }


    private List<Integer> getColors(PApplet processing) {
        List<Integer> colors = new ArrayList<>();
        for (Cluster cluster: kMeansResult.getClusters()) {
            float r = cluster.getCentroid().x;
            float g = cluster.getCentroid().y;
            float b = cluster.getCentroid().z;
            colors.add(processing.color(r, g, b));
        }
        return colors;
    }
}
