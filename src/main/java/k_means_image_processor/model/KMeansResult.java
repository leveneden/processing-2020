package k_means_image_processor.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class KMeansResult {

    private final List<Cluster> clusters;
    private final float WCSS;
}
