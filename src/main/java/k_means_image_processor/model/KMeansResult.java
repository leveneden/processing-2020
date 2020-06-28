package k_means_image_processor.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import processing.core.PVector;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class KMeansResult {

    private final List<Cluster> cluster;
    // private final double WCSS;

    public float getWCSS() {
        // todo: calculate. Maybe change for a BigDecimal if required

        return 0;
    }
}
