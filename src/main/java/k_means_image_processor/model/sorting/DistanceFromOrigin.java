package k_means_image_processor.model.sorting;

import processing.core.PVector;

import java.util.Comparator;

public class DistanceFromOrigin implements Comparator<PVector> {

    private static PVector origin = new PVector();

    @Override
    public int compare(PVector vectorA, PVector vectorB) {
        return (int) (origin.dist(vectorA) - origin.dist(vectorB));
    }
}
