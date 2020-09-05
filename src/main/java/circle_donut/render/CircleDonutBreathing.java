package circle_donut.render;

import circle_donut.model.CircleDonut;
import circle_donut.model.settings.CircleDonutSettings;
import circle_donut.render.settings.CircleDonutBreathingSettings;
import common.stateful.Renderable;
import processing.core.PApplet;

public class CircleDonutBreathing implements Renderable {

    private CircleDonutBreathingSettings settings;
    private PApplet processing;

    private float currentOffset = 270;
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
        return CIRCLES_DIAMETER_CONSTANT + (getCentralNegativeSpaceDiameter() / 2);
    }

    private float getCentralNegativeSpaceDiameter() {
        float sine = PApplet.map(PApplet.sin(PApplet.radians(currentOffset)), -1, 1, 0,1);
        return settings.centralNegativeSpaceDiameter + (sine * settings.breathingExpansionDistance);
    }

    private void increaseCurrentOffset() {
        currentOffset = ((currentOffset - 90 + settings.breathingsSpeed) % 360) + 90;
    }

    private float createCirclesDiameterConstant() {
        return (settings.centralNegativeSpaceDiameter / 2f) + settings.circlesDiameter;
    }

    public boolean isFullyExpanded() {
        return currentOffset == 450;
    }

    public boolean isFullyContracted() {
        return currentOffset == 90;
    }
}
