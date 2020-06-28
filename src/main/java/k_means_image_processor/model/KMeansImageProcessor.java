package k_means_image_processor.model;

import lombok.RequiredArgsConstructor;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

// todo: introduce the abstraction of a cluster please, you know it's gonna be messy if you decide not to. It will cost
//  you runtime.
@RequiredArgsConstructor
public class KMeansImageProcessor {

    private PApplet processing;

    public KMeansResult process(int k, String imagePath) {
        if (k<1) return new KMeansResult(new ArrayList<>());
        return calculateMeans(k, getPixelsAsPVectors(imagePath));
    }

    private List<PVector> getPixelsAsPVectors(String imagePath) {
        return parseToPVectors(getPixels(imagePath));
    }

    private int[] getPixels(String imagePath) {
        PImage image = processing.loadImage(imagePath);
        image.loadPixels();
        return image.pixels;
    }

    private List<PVector> parseToPVectors(int[] pixels) {
        List<PVector> vectors = new ArrayList<>();
        for (int pixel: pixels)
            vectors.add(new PVector(
                processing.red(pixel),
                processing.green(pixel),
                processing.blue(pixel)
            ));
        return vectors;
    }

    private KMeansResult calculateMeans(int k, List<PVector> vectors) {
        // get first centroids
        // iterate

        return null;
    }

    private List<PVector> deterministicallyGenerateCentroids(int amountOfCentroids, List<PVector> dataset) {
        List <PVector> centroids = new ArrayList<>();

        centroids.add(getAverage(dataset));

        int remainingCentroidsToFind = amountOfCentroids - 1;

        while (remainingCentroidsToFind > 0) {

            centroids.add(getVectorInDatasetFarthestFromAllCentroids(dataset, centroids));

            remainingCentroidsToFind--;
        }



        return null;
    }

    private PVector getAverage(List<PVector> vectors) {

        int timesDivided = 1;
        PVector average = new PVector();

        for (PVector vector: vectors) {
            PVector copy = vector.copy();
            average.add(
                    copy.sub(average).div(timesDivided)
            );
            timesDivided++;
        }

        return average;
    }

    private PVector getVectorInDatasetFarthestFromAllCentroids(List<PVector> dataset, List<PVector> centroids) {



        return null;
    }
}
