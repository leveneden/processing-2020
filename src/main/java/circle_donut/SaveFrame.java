package circle_donut;

import processing.core.PImage;

public class SaveFrame implements Runnable {

    private final PImage frame;
    private final int frameCount;
    private final String OUTPUT_DIR = "src/main/resources/circle_donut/render/";

    public SaveFrame(PImage frame, int frameCount) {
        this.frame = frame;
        this.frameCount = frameCount;
    }

    @Override
    public void run() {
        frame.save(String.format(OUTPUT_DIR + "frame_%04d.png", frameCount));
    }
}
