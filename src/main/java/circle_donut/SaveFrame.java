package circle_donut;

import processing.core.PImage;

public class SaveFrame implements Runnable {

    private PImage frame;
    private int frameCount;
    private final String OUTPUT_DIR = "src/main/resources/circle_donut/render/";

    public SaveFrame(PImage frame, int frameCount) {
        this.frame = frame;
        this.frameCount = frameCount;
    }

    @Override
    public void run() {
        frame.save(String.format(OUTPUT_DIR + "frame_%03d.png", frameCount));
    }
}
