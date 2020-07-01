package k_means_image_processor.model;

import lombok.Getter;
import lombok.Setter;
import processing.core.PVector;

import java.util.List;

@Getter
@Setter
public class Cluster {

    private PVector centroid;
    private List<PVector> vectors;

    public void addVector(PVector vector) {
        vectors.add(vector);
    }
}
