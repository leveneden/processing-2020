package tracing_eyes.model;

import common.model.Drawable;
import common.model.Updatable;
import lombok.Data;
import processing.core.PVector;

import java.awt.Point;

@Data
public class Eye implements Drawable, Updatable {

    private Point position;
    private int diameter;
    private Pupil pupil;
    private Point lookingAt;

    public Eye(Point position, int diameter, int pupilDiameter, Point lookingAt) throws InstantiationException {

        if (pupilDiameter > diameter)
            throw new InstantiationException("The pupil can't be wider than the eye itself.");

        this.position = position;
        this.diameter = diameter;
        this.lookingAt = lookingAt;
        this.pupil = new Pupil(new PVector(position.x, position.y), pupilDiameter);
        update();
    }

    @Override
    public void update() {
        updatePupil();
    }

    private void updatePupil() {

    }
}
