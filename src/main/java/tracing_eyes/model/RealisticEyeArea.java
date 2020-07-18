package tracing_eyes.model;

import common.stateless.Drawable;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class RealisticEyeArea implements Drawable {

    private PVector position;
    private RealisticEye eye;
    private PImage skinSprite;
    private PImage scleraSprite;

    public RealisticEyeArea(PVector position, PVector lookingAt) throws InstantiationException {

        this.position = position;
        eye = new RealisticEye(position.copy().add(new PVector(139, 91)), 118.5f,62, lookingAt);
    }

    private void initializeSprites(PApplet processing) {
        if (skinSprite == null)
            skinSprite = processing.loadImage("src/main/resources/tracing_eyes/sprites/skin.png");

        if (scleraSprite == null)
            scleraSprite = processing.loadImage("src/main/resources/tracing_eyes/sprites/sclera.png");
    }

    @Override
    public void draw(PApplet processing) {
        initializeSprites(processing);

        // todo: draw
        processing.image(scleraSprite, position.x, position.y);
        eye.draw(processing);
        processing.image(skinSprite, position.x, position.y);
    }

    public void setLookingAt(PVector lookingAt) {
        eye.setLookingAt(lookingAt);
    }

    public void setPosition(PVector position) {
        this.position = position;
        eye.setPosition(position.copy().add(new PVector(139, 91)));

    }
}
