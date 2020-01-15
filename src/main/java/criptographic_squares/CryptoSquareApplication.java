package criptographic_squares;

import processing.core.PApplet;

import java.awt.*;

public class CryptoSquareApplication extends PApplet {

    public void settings() {
        size(512, 512);
    }

    public void draw() {
        rect(0,0, 50, 10);
        noLoop();
    }

    public static void main(String[] passedArgs) {
        String[] appletArgs = new String[]{"criptographic_squares.CryptoSquareApplication"};
        PApplet.main(appletArgs);




    }
}
