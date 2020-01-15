package criptographic_squares.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.Point;

@Data
@AllArgsConstructor
public class Stick implements Drawable {

    private Point position;
    private int width, height;
    private boolean isPresent;

}
