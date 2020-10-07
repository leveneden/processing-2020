package circle_donut;

import circle_donut.render.CircleDonutBreathing;
import circle_donut.render.settings.CircleDonutBreathingSettings;
import lombok.SneakyThrows;
import processing.core.PApplet;

import java.awt.Point;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CircleDonutApplication extends PApplet {

    private CircleDonutBreathing breather;
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private final String OUTPUT_DIR = "src/main/resources/circle_donut/";

    /*
        Rendered with:
        1. ffmpeg -f image2 -r 30 -i frame_%04d.png -vcodec libx264 -profile:v high444 -refs 16 -crf 0 -preset ultrafast render.mp4
        2. ffmpeg -i render.mp4 -pix_fmt yuv420p -vcodec libx264 -preset veryslow uploadable.mp4
     */

    @Override
    public void settings() {
        size(1024, 1024);
    }

    @Override
    public void setup() {
        int green = color(75, 184, 75);
        int blue = color(42, 195, 203);
        int red = color(221, 61, 47);

        background(20);
        CircleDonutBreathingSettings renderSettings = CircleDonutBreathingSettings.builder()
                .center(new Point(width/2, height/2))
                .centralNegativeSpaceDiameter(0)
                .numberOfCircles(200)
                .circlesDiameter(440)
                .strokeWeight(1)
                .colors(new int[] {blue, green})
                .breathingExpansionDistance(880)
                .breathingsSpeed(1)
                .build();

        breather = new CircleDonutBreathing(renderSettings, this);
    }

    @SneakyThrows
    @Override
    public void draw() {

        for (int i =0; i<1; i++) {
            background(20);
            breather.render();
            save(OUTPUT_DIR + "test.png");
            //saveFrame(i);
        }

        Thread.sleep(10000);

        noLoop();
        System.exit(0);
    }

    private void saveFrame(int frameCount) {
        executorService.execute(new SaveFrame(get(), frameCount));
    }

    public static void main(String... art) {
        PApplet.main("circle_donut.CircleDonutApplication");
    }
}
