package k_means_image_processor.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import processing.core.PVector;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class KMeansResult {

    private final List<PVector> centroids;
    private final double WCSS;
}
