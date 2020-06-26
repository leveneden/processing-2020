package k_means_image_processor.model;

import lombok.RequiredArgsConstructor;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.List;

@RequiredArgsConstructor
public class KMeansImageProcessor {

    private PApplet processing;

    public KMeansResult process(int k, String imagePath) {
        return null;
    }

    private List<PVector> getPixelsAsPVectors(String imagePath) {
        return null;
    }
}
