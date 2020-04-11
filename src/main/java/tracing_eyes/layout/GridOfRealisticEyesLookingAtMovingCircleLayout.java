package tracing_eyes.layout;

import common.Drawable;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import tracing_eyes.model.RealisticEyeArea;

public class GridOfRealisticEyesLookingAtMovingCircleLayout implements Drawable {

    @Override
    public void draw(PApplet processing) {
        int frameCount = 0;
        while (frameCount < 300) {
            PImage frame = processing.loadImage(String.format("src/main/resources/tracing_eyes/render/frames/big/frame_%03d.png", frameCount));
            frame.resize(720, 0);
            processing.image(frame, 0, 0);
            processing.save(String.format("src/main/resources/tracing_eyes/render/frames/medium/frame_%03d.png", frameCount));
            frameCount++;
        }
        processing.noLoop();
        System.exit(0);
    }

    private void renderBigFrames(PApplet processing) {
        int frameCount = 0;
        int distanceFromCenter = 672;
        PVector center = new PVector(processing.width/2, processing.height/2);
        PVector focalPoint = new PVector();
        PImage watermark = processing.loadImage("src/main/resources/tracing_eyes/sprites/frame-watermark.png");

        for (int degrees = 0; degrees < 3600; degrees += 12) {
            focalPoint.x = center.x + PApplet.sin(PApplet.radians(degrees)) * distanceFromCenter;
            focalPoint.y = center.y + PApplet.cos(PApplet.radians(degrees)) * distanceFromCenter;
            drawEyesGridLookingAt(focalPoint, processing);
            processing.image(watermark, 0, 0);
            processing.save(String.format("src/main/resources/tracing_eyes/render/frames/big/frame_%03d.png", frameCount));
            frameCount++;
        }
    }

    private void drawEyesGridLookingAt(PVector focalPoint, PApplet processing) {
        try {
            int eyeWidth = 256;
            int eyeHeight = 192;

            for (int x = 0; x < processing.width; x += eyeWidth) {
                for (int y = 0; y < processing.height; y += eyeHeight) {
                    new RealisticEyeArea(new PVector(x, y), new PVector(focalPoint.x, focalPoint.y)).draw(processing);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
