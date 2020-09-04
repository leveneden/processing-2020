package circle_donut.render.settings;

import lombok.Builder;

import java.awt.Point;

@Builder
public class CircleDonutBreathingSettings {
    public Point center;
    public float centralNegativeSpaceDiameter;
    public int numberOfCircles;
    public float circlesDiameter;
    public int strokeWeight;
    public int[] colors;
    public float breathingsSpeed;
    public int breathingExpansionDistance;

    public boolean areValid() {
        return (center != null && colors.length > 1);
    }
}
