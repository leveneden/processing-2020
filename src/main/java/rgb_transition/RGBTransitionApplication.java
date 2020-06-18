package rgb_transition;


import processing.core.PApplet;
import rgb_transition.model.RGBColorMapper;

import java.util.List;

public class RGBTransitionApplication extends PApplet {

    int i = 0;

    @Override
    public void settings() {
        size(512, 512);
    }

    @Override
    public void setup() {
    }

    @Override
    public void draw() {

        RGBColorMapper colorMapper = new RGBColorMapper();

        colorMapper.draw(this);

        List<Integer> colorGradient = colorMapper.getColorGradient();

        if (i == colorGradient.size() - 1) {
            i = 0;
        }
        background(colorGradient.get(i));

        i++;


        System.out.println(i);


        // frameRate(5);
        // noLoop();

        // System.exit(0);
    }

    public static void main(String... art) {
        PApplet.main("rgb_transition.RGBTransitionApplication");
    }
}
