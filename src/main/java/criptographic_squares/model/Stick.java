package criptographic_squares.model;

import common.Drawable;
import lombok.AllArgsConstructor;
import lombok.Data;
import processing.core.PApplet;

import java.awt.Point;

@Data
@AllArgsConstructor
public class Stick implements Drawable {

    private Point position;
    private int width, height;
    private boolean isPresent;

    @Override
    public void draw(PApplet processing) {
        processing.noStroke();
        processing.fill(0);
        if (isPresent) processing.rect(position.x, position.y, width, height);
    }
}
