package circle_donut;

import circle_donut.model.CircleDonut;
import circle_donut.model.settings.CircleDonutSettings;
import circle_donut.render.CircleDonutBreathing;
import circle_donut.render.settings.CircleDonutBreathingSettings;
import common.stateful.Renderable;
import lombok.SneakyThrows;
import processing.core.PApplet;

import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CircleDonutApplication extends PApplet {

    private Renderable breather;
    // fixme: delete me
    private CircleDonut donut;
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private final String OUTPUT_DIR = "src/main/resources/circle_donut/";

    @Override
    public void settings() {
        size(1024, 1024);
    }

    @Override
    public void setup() {
        background(20);
        CircleDonutBreathingSettings renderSettings = CircleDonutBreathingSettings.builder()
                .center(new Point(width/2, height/2))
                .centralNegativeSpaceDiameter(0)
                .numberOfCircles(200)
                .circlesDiameter(440)
                .strokeWeight(1)
                .colors(new int[] {color(75, 184, 75), color(42, 195, 203)})
                .breathingExpansionDistance(880)
                .breathingsSpeed(1)
                .build();

        CircleDonutSettings donutSettings = CircleDonutSettings.builder()
                .strokeWeight(1)
                .numberOfCircles(200)
                .colors(new int[] {color(75, 184, 75), color(42, 195, 203)})
                .circlesDiameter(440)
                .centralNegativeSpaceDiameter(0)
                .center(new Point(width/2, height/2))
                .build();


        donut = new CircleDonut(donutSettings, this);

        breather = new CircleDonutBreathing(renderSettings, this);
    }

    @SneakyThrows
    @Override
    public void draw() {

        for (int i =0; i<1; i++) {
            background(20);
            donut.draw();
            //breather.render();
            save(OUTPUT_DIR + "IG.png");
            //saveFrame(i);
        }

        Thread.sleep(10000);

        noLoop();
        //System.exit(0);
    }

    private void saveFrame(int frameCount) {
        executorService.execute(new SaveFrame(get(), frameCount));
        //boolean a = get().save(String.format(OUTPUT_DIR + "render/frame_%03d.png", frameCount));
        //System.out.println(a);
    }

    public static void main(String... art) {
        PApplet.main("circle_donut.CircleDonutApplication");
    }
}
