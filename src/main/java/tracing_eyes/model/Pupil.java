package tracing_eyes.model;

import common.Drawable;
import common.Updatable;
import lombok.AllArgsConstructor;
import lombok.Data;
import processing.core.PApplet;
import processing.core.PVector;

@Data
@AllArgsConstructor
public class Pupil implements Updatable, Drawable {

    private PVector position;
    private float diameter;
    private Sclera sclera;
    private PVector lookingAt;

    @Override
    public void update() {
        updatePosition();
    }

    @Override
    public void draw(PApplet processing) {
        processing.noStroke();
        processing.fill(0);

        processing.ellipse(this.getPosition().x, this.getPosition().y, this.getDiameter(), this.getDiameter());
    }

    protected void updatePosition() {
        // if lookingAt is within the eye radius - pupil radius
        if (lookingAt.dist(sclera.getPosition()) < (sclera.getDiameter() - diameter) / 2) {
            // position = lookingAt
            position = lookingAt.copy();
        } else {
            // else
            // Get the vector V = (lookingAt - position)
            // Normalize V
            // Multiply V * (eye radius - pupil radius)
            // position = position + V
            position = sclera.getPosition().copy().add(lookingAt.sub(position).normalize().mult((sclera.getDiameter() - diameter) / 2));
        }
    }

    // setters
    public void setPosition(PVector position) {
        this.position = position;
        update();
    }

    public void setDiameter(float diameter) {
        if (diameter < sclera.getDiameter()) {
            this.diameter = diameter;
        } else {
            throw new IllegalArgumentException("The pupil can't be wider than the eye itself.");
        }
        update();
    }

    public void setLookingAt(PVector lookingAt) {
        this.lookingAt = lookingAt;
        update();
    }
}
