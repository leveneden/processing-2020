package tracing_eyes.model;

import common.model.Drawable;
import common.model.Updatable;
import lombok.AllArgsConstructor;
import lombok.Data;
import processing.core.PVector;

import java.awt.Point;

@Data
public class Eye implements Drawable, Updatable {

    private Point position;
    private int diameter;
    private Pupil pupil;
    private PVector lookingAt;

    public Eye(Point position, int diameter, int pupilDiameter, PVector lookingAt) throws InstantiationException {

        if (pupilDiameter > diameter)
            throw new InstantiationException("The pupil can't be wider than the eye itself.");

        this.position = position;
        this.diameter = diameter;
        this.lookingAt = lookingAt;
        this.pupil = new Pupil(new PVector(position.x, position.y), pupilDiameter);
        update();
    }

    // setters
    @Override
    public void update() {
        updatePupil();
    }

    private void updatePupil() {
        pupil.update();
    }

    public void setPosition(Point position) {
        this.position = position;
        update();
    }

    public void setDiameter(int diameter) {
        if (diameter > pupil.getDiameter()) {
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

    @Data
    @AllArgsConstructor
    public class Pupil implements Drawable, Updatable {

        private PVector position;
        private int diameter;

        private void updatePosition() {
            // if Eye.this.lookingAt is within the eye radius - pupil radius
                // position = lookingAt
            // else
                // Get the vector V = (position - Eye.this.lookingAt)
                // Normalize V
                // Multiply V * (eye radius - pupil radius)
                // position = V
            if (Eye.this.lookingAt.dist(new PVector(Eye.this.position.x, Eye.this.position.y)) < (float) ( Eye.this.diameter - diameter)/2 ) {
                position = lookingAt.copy();
            } else {
                position = new PVector(Eye.this.position.x, Eye.this.position.y).add(Eye.this.lookingAt.sub(position).normalize().mult((float) ( Eye.this.diameter - diameter)/2));
            }
        }

        @Override
        public void update() {
            updatePosition();
        }


    }
}
