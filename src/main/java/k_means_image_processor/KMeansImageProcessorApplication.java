package k_means_image_processor;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class KMeansImageProcessorApplication extends PApplet {

    @Override
    public void settings() {
        size(1024, 1024);
    }

    @Override
    public void setup() {
    }

    @Override
    public void draw() {
        noLoop();
        System.exit(0);
    }

    public static void main(String... art) {
        PApplet.main("k_means_image_processor.KMeansImageProcessorApplication");
    }

    // todo: clean this mess

    private void addRandomPVectorTo(List<PVector> centroids) {
        boolean add = centroids.add(new PVector((float) Math.random() * width, (float) Math.random() * height));
    }

    private void debug(PVector vector) {
        System.out.println("x: " + vector.x);
        System.out.println("y: " + vector.y);
        System.out.println("z: " + vector.z);
        System.out.println();
    }

    private PVector getFurthestPVectorByComputingLongestNearestDistance(List<PVector> centroids, List<PVector> dataPoints) {
        // for each datapoint get the nearest centroid
        // if this distance to the nearest centroid is LONGER than the previous one, hold a reference to this distance and this datapoint
        float longestDistanceToNearestCentroid = 0;
        PVector furthestPoint = null;
        for (PVector dataPoint: dataPoints) {
            float distanceToNearestCentroid = dataPoint.dist(getNearestTo(dataPoint, centroids));
            if (distanceToNearestCentroid > longestDistanceToNearestCentroid) {
                longestDistanceToNearestCentroid = distanceToNearestCentroid;
                furthestPoint = dataPoint;
            }
        }

        return furthestPoint;
    }

    private PVector getNearestTo(PVector thisPoint, List<PVector> otherPoints) {
        float shortestDistance = Float.MAX_VALUE;
        PVector nearestVector = null;
        for (PVector point: otherPoints) {
            if (thisPoint.dist(point) < shortestDistance) {
                shortestDistance = thisPoint.dist(point);
                nearestVector = point;
            }
        }
        return nearestVector;
    }

    private PVector getFurthestPVectorByComputingLargestSumOfDistances(List<PVector> centroids, List<PVector> dataPoints) {
        float largestSumOfOfDistances = Float.MIN_VALUE;
        PVector furthestPVector = null;
        for (PVector dataPoint: dataPoints) {
            float sumOfDistances = 0;
            for (PVector centroid: centroids) {
                sumOfDistances += dataPoint.dist(centroid);
            }
            if (sumOfDistances > largestSumOfOfDistances) {
                largestSumOfOfDistances = sumOfDistances;
                furthestPVector = dataPoint;
            }
        }
        return furthestPVector;
    }

    private PVector getFurthestPVectorByComputingLargestSumOfDistancesSquared(List<PVector> centroids, List<PVector> dataPoints) {
        float largestSumOfOfDistancesSquared = Float.MIN_VALUE;
        PVector furthestPVector = null;
        for (PVector dataPoint: dataPoints) {
            float sumOfDistances = 0;
            for (PVector centroid: centroids) {
                sumOfDistances += Math.pow(dataPoint.dist(centroid), 2);
            }
            if (sumOfDistances > largestSumOfOfDistancesSquared) {
                largestSumOfOfDistancesSquared = sumOfDistances;
                furthestPVector = dataPoint;
            }
        }
        return furthestPVector;
    }

    private void testAverageCentroids() {
        List<PVector> vectors = new ArrayList<>();

        vectors.add(new PVector(14, 5, 19));
        vectors.add(new PVector(6, 17, 4));
        vectors.add(new PVector(2, 8, 11));
        vectors.add(new PVector(9, 16, 3));

        int timesDivided = 1;
        PVector average = new PVector();

        for (PVector vector: vectors) {
            PVector copy = vector.copy();
            average.add(
                    copy.sub(average).div(timesDivided)
            );
            timesDivided++;
        }

        for (PVector vector: vectors) {
            debug(vector);
        }

        debug(average);
    }

    private void visualizeFurthestLocationAlgorithms(int testNum) {
        background(211,211,211);
        noStroke();
        fill(255);
        int radius = 20;
        int reducedRadius = 18;

        List<PVector> datapoints = new ArrayList<>();
        List<PVector> centroids = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            addRandomPVectorTo(centroids);
        }

        for (int i = 0; i < 200; i++) {
            addRandomPVectorTo(datapoints);
        }

        for (PVector datapoint: datapoints) {
            ellipse(datapoint.x, datapoint.y, radius, radius);
        }

        fill(0);
        for (PVector centroid: centroids) {
            ellipse(centroid.x, centroid.y, radius, radius);
        }

        fill(255, 0, 0);
        PVector methodA = getFurthestPVectorByComputingLargestSumOfDistances(centroids, datapoints);
        ellipse(methodA.x, methodA.y, reducedRadius, reducedRadius);

        fill(0, 255, 0);
        PVector methodB = getFurthestPVectorByComputingLargestSumOfDistancesSquared(centroids, datapoints);
        ellipse(methodB.x, methodB.y, reducedRadius -2, reducedRadius -2);

        fill(0, 0, 255);
        PVector methodC = getFurthestPVectorByComputingLongestNearestDistance(centroids, datapoints);
        ellipse(methodC.x, methodC.y, reducedRadius - 4, reducedRadius - 4);

        // todo: consider turning this into three custom modes of initialization for the k means before deleting
        debug(methodA);
        debug(methodB);
        debug(methodC);

        System.out.println(methodA.equals(methodC));

        save("test " + testNum + ".png");
    }
}