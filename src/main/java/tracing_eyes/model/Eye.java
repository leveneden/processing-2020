package tracing_eyes.model;

import common.model.Drawable;
import common.model.Updatable;
import lombok.AllArgsConstructor;
import lombok.Data;
import processing.core.PVector;

@Data
public class Eye implements Drawable, Updatable {

    private PVector position;
    private int diameter;
    private Pupil pupil;

    public Eye(PVector position, int diameter, int pupilDiameter, PVector lookingAt) throws InstantiationException {

        if (pupilDiameter > diameter)
            throw new InstantiationException("The pupil can't be wider than the eye itself.");

        this.position = position;
        this.diameter = diameter;
        this.pupil = new Pupil(position.copy(), pupilDiameter, lookingAt);
        update();
    }

    @Override
    public void update() {
        updatePupil();
    }

    // setters
    private void updatePupil() {
        pupil.update();
    }

    public void setPosition(PVector position) {
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

    @Data
    @AllArgsConstructor
    public class Pupil implements Drawable, Updatable {

        private PVector position;
        private int diameter;
        private PVector lookingAt;

        private void updatePosition() {
            // if lookingAt is within the eye radius - pupil radius
            if (lookingAt.dist(Eye.this.position) < (float) ( Eye.this.diameter - diameter)/2 ) {
                // position = lookingAt
                position = lookingAt.copy();
            } else {
            // else
                // Get the vector V = (lookingAt - position)
                // Normalize V
                // Multiply V * (eye radius - pupil radius)
                // position = position + V
                position = Eye.this.position.copy().add(lookingAt.sub(position).normalize().mult((float) (Eye.this.diameter - diameter) / 2));
            }
        }

        @Override
        public void update() {
            updatePosition();
        }

        // setters
        public void setPosition(PVector position) {
            this.position = position;
            update();
        }

        public void setDiameter(int diameter) {
            if (diameter < Eye.this.diameter) {
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
}
