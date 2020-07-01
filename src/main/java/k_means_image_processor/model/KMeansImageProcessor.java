package k_means_image_processor.model;

import k_means_image_processor.model.sorting.DistanceFromOrigin;
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
        if (k<1) return new KMeansResult(new ArrayList<>(), 0);
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
        // get first clusters
        List<Cluster> clusters = getFirstClusters(k, vectors);
        // iterate
        KMeansResult result = iterate(clusters, vectors);

        return null;
    }

    private KMeansResult iterate(List<Cluster> clusters, List<PVector> vectors) {
        // define a method by which to check if the centroids are in the same place as in the previous iterations
        boolean allCentroidsAreInTheirLastPosition = false;
        // loop
        while (!allCentroidsAreInTheirLastPosition) {
            // get previous centroids
            List<PVector> lastCentroids = getCentroidsFromClusters(clusters);
            // erase points in the cluster
            clearAllVectorsFromAllClusters(clusters);
            // find nearest points to centroids in clusters
            addAllVectorsToTheirNearestCluster(clusters, vectors);
            // relocate centroids
            relocateAllClustersCentroids(clusters);
            // get current centroids
            List<PVector> currentCentroids = getCentroidsFromClusters(clusters);
            // override the check
            allCentroidsAreInTheirLastPosition = areAllCentroidsInTheSamePosition(lastCentroids, currentCentroids);
        }
        // calculate WCSS
        float wcss = getAverageWCSS(clusters);
        // add clusters and WCSS to KMeansResult
        // return KMeansResult
        return new KMeansResult(clusters, wcss);
    }

    private float getAverageWCSS(List<Cluster> clusters) {
        int timesDivided = 1;
        float average = 0;
        for (Cluster cluster: clusters) {
            average += (getWCSS(cluster) - average)/timesDivided;
            timesDivided++;
        }
        return average;
    }

    // todo: consider refactoring these cluster methods to be Delegated
    //  otherwise you might want to replicate the functionality in some other class
    //  and it would be cumbersome
    private float getWCSS(Cluster cluster) {
        float wcss = 0;
        PVector centroid = cluster.getCentroid();
        for (PVector vector: cluster.getVectors()) {
            wcss += Math.pow(vector.dist(centroid), 2);
        }
        return wcss;
    }

    private boolean areAllCentroidsInTheSamePosition(List<PVector> lastCentroids, List<PVector> currentCentroids) {
        sort(lastCentroids);
        sort(currentCentroids);
        assert lastCentroids.size() == currentCentroids.size();
        for (int i = 0; i < lastCentroids.size(); i++) {
            float distanceFromPreviousToCurrentCentroid = lastCentroids.get(i).dist(currentCentroids.get(i));
            if (distanceFromPreviousToCurrentCentroid > 0.002) {
                return false;
            }
        }
        return true;
    }

    private void sort(List<PVector> lastCentroids) {
        lastCentroids.sort(new DistanceFromOrigin());
    }

    private List<PVector> getCentroidsFromClusters(List<Cluster> clusters) {
        List<PVector> centroids = new ArrayList<>();
        for (Cluster cluster: clusters) {
            centroids.add(cluster.getCentroid().copy());
        }
        return centroids;
    }

    private void clearAllVectorsFromAllClusters(List<Cluster> clusters) {
        for (Cluster cluster: clusters) {
            clearAllVectorsFromCluster(cluster);
        }
    }

    private void clearAllVectorsFromCluster(Cluster cluster) {
        cluster.setVectors(new ArrayList<>());
    }

    private void relocateAllClustersCentroids(List<Cluster> clusters) {
        for (Cluster cluster: clusters) {
            relocateClusterCentroid(cluster);
        }
    }

    private void relocateClusterCentroid(Cluster cluster) {
        cluster.setCentroid(getAverage(cluster.getVectors()));
    }

    private void addAllVectorsToTheirNearestCluster(List<Cluster> clusters, List<PVector> vectors) {
        for (PVector vector: vectors) {
            float smallestDistance = Float.MAX_VALUE;
            Cluster nearestCluster = null;
            for (Cluster cluster: clusters) {
                float distanceFromVectorToClusterCentroid = cluster.getCentroid().dist(vector);
                if (distanceFromVectorToClusterCentroid < smallestDistance) {
                    smallestDistance = distanceFromVectorToClusterCentroid;
                    nearestCluster = cluster;
                }
            }
            nearestCluster.addVector(vector);
        }
    }

    private List<Cluster> getFirstClusters(int k, List<PVector> vectors) {
        return getClustersForCentroids(deterministicallyGenerateCentroids(k, vectors));
    }

    private List<Cluster> getClustersForCentroids(List<PVector> centroids) {
        List<Cluster> clusters = new ArrayList<>();
        for (PVector centroid: centroids) {
            Cluster cluster = new Cluster();
            cluster.setCentroid(centroid);
            clusters.add(cluster);
        }
        return clusters;
    }

    private List<PVector> deterministicallyGenerateCentroids(int amountOfCentroids, List<PVector> dataset) {
        List <PVector> centroids = new ArrayList<>();
        centroids.add(getAverage(dataset));

        int remainingCentroidsToFind = amountOfCentroids - 1;

        while (remainingCentroidsToFind > 0) {
            centroids.add(getVectorInDatasetFarthestFromAllCentroids(dataset, centroids).copy());
            remainingCentroidsToFind--;
        }

        return centroids;
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

    // todo: add a third parameter for the initialization mode and extract this method
    private PVector getVectorInDatasetFarthestFromAllCentroids(List<PVector> dataPoints, List<PVector> centroids) {
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


}
