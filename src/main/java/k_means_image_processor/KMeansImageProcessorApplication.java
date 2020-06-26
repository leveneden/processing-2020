package k_means_image_processor;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class KMeansImageProcessorApplication extends PApplet {

    @Override
    public void settings() {
        size(512, 512);
    }

    @Override
    public void setup() {

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

    private void debug(PVector vector) {
        System.out.println("x: " + vector.x);
        System.out.println("y: " + vector.y);
        System.out.println("z: " + vector.z);
        System.out.println();
    }

    @Override
    public void draw() {
        noLoop();
        //System.exit(0);
    }

    public static void main(String... art) {
        PApplet.main("k_means_image_processor.KMeansImageProcessorApplication");
    }
}