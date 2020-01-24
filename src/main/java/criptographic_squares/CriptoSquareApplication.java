package criptographic_squares;

import common.view.AbstractDrawer;
import criptographic_squares.model.StickFigure;
import criptographic_squares.view.StickFigureDrawer;
import processing.core.PApplet;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class CriptoSquareApplication extends PApplet {

    private AbstractDrawer drawer;

    @Override
    public void settings() {
        size(512, 512);
    }

    @Override
    public void setup() {
        noStroke();
        fill(0);
        background(255);
        frameRate(5);

        drawer = new StickFigureDrawer(this);
    }

    @Override
    public void draw() {

        for (int i = 0; i < 50; i++) {
            drawRandomShapes(2, 10, 90);
            // save("src/main/resources/criptographic_squares/render/frame_" + String.format("%03d", i) + ".png");
        }

        noLoop();
        System.exit(0);
    }

    public static void main(String... art) {
        PApplet.main("criptographic_squares.CriptoSquareApplication");
    }

    // --------------------- Impl 1

    /**
     * Places {@link criptographic_squares.model.StickFigure} objects
     * in a square grid of 64x64 (4096) objects.
     *
     * @param innerSquareSize - This parameter is used for calculating the size of
     *                          {@link criptographic_squares.model.StickFigure}
     *                          @see StickFigure#getInnerSquareSize().
     *
     * @param margin - The separation in pixels between shapes drawn shapes.
     */
    private void placeShapesInSquareGridOrderedByNumberOfSticks(int innerSquareSize, int margin) {

        List<String> orderedStates = initializeBinaryStringsOrderedByNumberOfSticks();
        int currentIndex = 0;

        /*

        // fills squares as a background template

        fill(255);

        for (int col = 0; col < ((innerSquareSize * 5) + margin) * 64 ; col = col + (innerSquareSize * 5) + margin) {
            for (int row = 0; row < ((innerSquareSize * 5) + margin) * 64 ; row = row + (innerSquareSize * 5) + margin) {
                    drawer.draw(
                            new StickFigure(
                                    new Point(row, col),
                                    innerSquareSize,
                                    orderedStates.get((orderedStates.size()-1))
                            )
                    );

            }
        }
        fill(0);

         */

        for (int col = 0; col < ((innerSquareSize * 5) + margin) * 64 ; col = col + (innerSquareSize * 5) + margin) {
            for (int row = 0; row < ((innerSquareSize * 5) + margin) * 64 ; row = row + (innerSquareSize * 5) + margin) {
                if (currentIndex < 4096) {
                    drawer.draw(
                            new StickFigure(
                                    new Point(row, col),
                                    innerSquareSize,
                                    orderedStates.get(currentIndex)
                            )
                    );
                    currentIndex++;
                } else {
                    break;
                }
            }
        }

    }

    private List<String> initializeBinaryStringsInDefaultOrder() {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < 4096; i++)
            result.add(toFormattedBinaryString(i));

        return result;
    }

    private String toFormattedBinaryString(int i) {
        return String.format("%12s", Integer.toBinaryString(i)).replace(' ', '0');
    }

    private List<String> orderByNumberOfSticks(List<String> binaryStates) {

        List<String> result = new ArrayList<>();

        for (int stickQuantity = 0; stickQuantity <= 12; stickQuantity++)
            for (int i = 0; i < 4096; i++)
                if (hasThisManyStick(stickQuantity, binaryStates.get(i)))
                    result.add(binaryStates.get(i));

        return result;
    }

    private List<String> initializeBinaryStringsOrderedByNumberOfSticks() {
        return orderByNumberOfSticks(initializeBinaryStringsInDefaultOrder());
    }

    private boolean hasThisManyStick(int stickQuantity, String binaryState) {
        return binaryState.length() - binaryState.replaceAll("1","").length() == stickQuantity;
    }

    // --------------------- End of Impl 1


    // --------------------- Impl 2

    /**
     * Draws random shapes each frame
     *
     * @param innerSquareSize - Each shape is of size (innerSquareSize * 5) in width and height
     * @param margin - Space between the shapes in pixels
     */
    private void drawRandomShapes(int innerSquareSize, int margin, int frame) {
        background(255);
        for (int i = frame; i < width - frame - innerSquareSize *5; i += innerSquareSize * 5 + margin)
            for (int j = frame; j < height - frame - innerSquareSize *5; j += innerSquareSize * 5 + margin)
                drawer.draw(
                        new StickFigure(
                                new Point(j, i),
                                innerSquareSize,
                                toFormattedBinaryString((int) (Math.random() * 4096))
                        )
                );

        visualizeFrame(frame);
    }

    private void visualizeFrame(int frame) {
        stroke(255, 0, 0);
        line(frame, 0, frame, height);
        line(width - frame, 0, width - frame, height);
        line(0, frame, width, frame);
        line(0, height - frame, width, height - frame);
        noStroke();
    }

    // --------------------- End of Impl 2

    /*
       FFMPEG NOTES:
    
       ffmpeg -i frame_%3d.png -vcodec mpeg4 render.avi // low fidelity output

       https://video.stackexchange.com/questions/7903/how-to-losslessly-encode-a-jpg-image-sequence-to-a-video-in-ffmpeg
       ffmpeg -framerate 5 -i frame_%03d.png -codec copy render.mkv // higher fidelity output
       ffmpeg -f image2 -r 5 -i frame_%03d.png -vcodec libx264 -profile:v high444 -refs 16 -crf 0 -preset ultrafast uploadable-render.mp4 // high fidelity and not uploadable
       https://superuser.com/questions/1331034/vlc-can-but-instagram-and-gallery-cannot-play-my-videos
       ffmpeg -i render.mp4 -pix_fmt yuv420p -vcodec libx264 -preset veryslow timelapse.mp4 // magical command that makes an mp4 video compatible with instagram
     */
}
