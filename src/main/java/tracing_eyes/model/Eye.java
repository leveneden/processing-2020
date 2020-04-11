package tracing_eyes.model;

import common.Drawable;
import common.Updatable;
import lombok.Data;
import processing.core.PApplet;
import processing.core.PVector;

@Data
public class Eye implements Updatable, Drawable {

    protected Sclera sclera;
    protected Pupil pupil;

    public Eye(PVector position, float diameter, float pupilDiameter, PVector lookingAt) throws InstantiationException {

        if (pupilDiameter > diameter)
            throw new InstantiationException("The pupil can't be wider than the eye itself.");

        this.sclera = new Sclera(position, diameter);
        this.pupil = new Pupil(position.copy(), pupilDiameter, sclera, lookingAt);
        update();
    }

    @Override
    public void update() {
        updatePupil();
    }

    @Override
    public void draw(PApplet processing) {
        drawSclera(processing);
        drawPupil(processing);
    }

    protected void updatePupil() {
        pupil.update();
    }

    protected void drawSclera(PApplet processing) {
        sclera.draw(processing);
    }

    protected void drawPupil(PApplet processing) {
        this.pupil.draw(processing);
    }

    // setters
    public void setPosition(PVector position) {
        this.sclera.setPosition(position);
        update();
    }

    public void setDiameter(float diameter) {
        if (diameter > pupil.getDiameter()) {
            this.sclera.setDiameter(diameter);
        } else {
            throw new IllegalArgumentException("The pupil can't be wider than the eye itself.");
        }
        update();
    }

    public void setLookingAt(PVector lookingAt) {
        pupil.setLookingAt(lookingAt);
    }
}
