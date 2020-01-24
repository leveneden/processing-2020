package common.view;

import common.model.Drawable;
import lombok.AllArgsConstructor;
import processing.core.PApplet;

@AllArgsConstructor
public abstract class AbstractDrawer {

    protected PApplet processing;

    public abstract void draw(Drawable drawable);
}
