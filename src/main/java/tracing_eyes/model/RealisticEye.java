package tracing_eyes.model;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;


public class RealisticEye extends Eye {

    private static PImage sprite;

    public RealisticEye(PVector position, float diameter, float pupilDiameter, PVector lookingAt) throws InstantiationException {
        super(position, diameter, pupilDiameter, lookingAt);

    }

    // I know this violates my own design but
    // it was the most straightforward way I could find
    // of walking around a bug I don't understand ¯\_(ツ)_/¯
    private void initializeSprite(PApplet processing) {
        if (sprite == null)
            sprite = processing.loadImage("src/main/resources/tracing_eyes/sprites/pupil.png");
    }

    @Override
    protected void drawPupil(PApplet processing) {
        initializeSprite(processing);
        processing.image(sprite, this.pupil.getPosition().x - (this.pupil.getDiameter()/2), this.pupil.getPosition().y - (this.pupil.getDiameter()/2));
    }

    @Override
    protected void drawSclera(PApplet processing) {
    }
}
