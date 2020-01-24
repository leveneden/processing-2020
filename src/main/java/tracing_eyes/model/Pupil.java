package tracing_eyes.model;

import common.model.Drawable;
import lombok.AllArgsConstructor;
import lombok.Data;
import processing.core.PVector;

@Data
@AllArgsConstructor
public class Pupil implements Drawable {

    private PVector position;
    private int diameter;
}
