package tracing_eyes.view;

import common.model.Drawable;
import common.view.AbstractDrawer;
import tracing_eyes.model.Eye;
import processing.core.PApplet;

public class EyeDrawer extends AbstractDrawer {

    public EyeDrawer(PApplet processing) {
        super(processing);
    }

    @Override
    public void draw(Drawable drawable) {
        Eye eye = (Eye) drawable;
        drawSclera(eye);
        drawPupil(eye);
    }

    private void drawSclera(Eye eye) {
        processing.noStroke();
        processing.fill(255);
        processing.ellipse(eye.getPosition().x, eye.getPosition().y, eye.getDiameter(), eye.getDiameter());
    }

    private void drawPupil(Eye eye) {
        processing.noStroke();
        processing.fill(0);

        processing.ellipse(eye.getPupil().getPosition().x, eye.getPupil().getPosition().y, eye.getPupil().getDiameter(), eye.getPupil().getDiameter());
    }
}
