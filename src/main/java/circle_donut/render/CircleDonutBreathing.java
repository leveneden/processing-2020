package circle_donut.render;

import circle_donut.model.CircleDonut;
import circle_donut.model.settings.CircleDonutSettings;
import circle_donut.render.settings.CircleDonutBreathingSettings;
import common.stateful.Renderable;
import processing.core.PApplet;

public class CircleDonutBreathing implements Renderable {

    private CircleDonutBreathingSettings settings;
    private PApplet processing;

    private float currentOffset;
    private final float CIRCLES_DIAMETER_CONSTANT;

    public CircleDonutBreathing(CircleDonutBreathingSettings settings, PApplet processing) {
        this.settings = settings;
        this.processing = processing;

        CIRCLES_DIAMETER_CONSTANT = createCirclesDiameterConstant();

        assert settings.areValid();
    }

    @Override
    public void render() {

        assert settings.areValid();

        new CircleDonut(buildCircleDonutSettings(), processing).draw();

        increaseCurrentOffset();

    }

    private CircleDonutSettings buildCircleDonutSettings() {
        CircleDonutSettings settings = CircleDonutSettings.builder()
                .center(this.settings.center)
                .centralNegativeSpaceDiameter(getCentralNegativeSpaceDiameter())
                .circlesDiameter(getProportionalCirclesDiameter())
                .colors(this.settings.colors)
                .numberOfCircles(this.settings.numberOfCircles)
                .strokeWeight(this.settings.strokeWeight)
                .build();

        assert settings.areValid();

        return settings;
    }

    private float getProportionalCirclesDiameter() {
        // System.out.println("C: " + CIRCLES_DIAMETER_CONSTANT + ". x/2: " + getCentralNegativeSpaceDiameter() / 2 + ". y: " + (CIRCLES_DIAMETER_CONSTANT - (getCentralNegativeSpaceDiameter() / 2)));

        return (CIRCLES_DIAMETER_CONSTANT + (getCentralNegativeSpaceDiameter() / 2));
    }

    private float getCentralNegativeSpaceDiameter() {
        float sine = PApplet.map(PApplet.sin(PApplet.radians(currentOffset)), -1, 1, 0,1);
        // fixme: remove me, I'm here for debugging purposes
        if (sine == 1) {
            System.out.println("upper bound. currentOffset = " + currentOffset + ". irclesDiameter = " + settings.circlesDiameter + ". centralNegativeSpace = " +  (settings.centralNegativeSpaceDiameter + (sine * settings.breathingExpansionDistance)));
        } else if (sine == 0) {
            System.out.println("lower bound. currentOffset = " + currentOffset + ". irclesDiameter = " + settings.circlesDiameter + ". centralNegativeSpace = " +  (settings.centralNegativeSpaceDiameter + (sine * settings.breathingExpansionDistance)));
        }

        return settings.centralNegativeSpaceDiameter + (sine * settings.breathingExpansionDistance);
    }

    private void increaseCurrentOffset() {
        currentOffset += settings.breathingsSpeed;
    }

    private float createCirclesDiameterConstant() {
        return (settings.centralNegativeSpaceDiameter / 2f) + settings.circlesDiameter;
    }
}
