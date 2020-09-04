package circle_donut.model.settings;

import lombok.Builder;

import java.awt.Point;

@Builder
public class CircleDonutSettings {
    public Point center;
    public float centralNegativeSpaceDiameter;
    public int numberOfCircles;
    public float circlesDiameter;
    public int strokeWeight;
    public int[] colors;

    public boolean areValid() {
        return (center != null && colors.length > 1);
    }
}
