package common.strategy;

import common.view.AbstractDrawer;
import processing.core.PApplet;

abstract public class AbstractDrawingStrategy {

    protected AbstractDrawer drawer;
    protected PApplet processing;

    public abstract void execute();
}
