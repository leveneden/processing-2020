package tracing_eyes.model;

import common.stateless.Drawable;
import lombok.AllArgsConstructor;
import lombok.Data;
import processing.core.PApplet;
import processing.core.PVector;

@Data
@AllArgsConstructor
public class Sclera implements Drawable {

    private PVector position;
    private float diameter;

    @Override
    public void draw(PApplet processing) {
        processing.noStroke();
        processing.fill(255);
        processing.ellipse(position.x, position.y, this.diameter, this.diameter);
    }
}
