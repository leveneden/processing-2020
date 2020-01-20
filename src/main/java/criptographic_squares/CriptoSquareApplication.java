package criptographic_squares;

import criptographic_squares.model.StickFigure;
import criptographic_squares.view.Drawer;
import criptographic_squares.view.impl.StickFigureDrawer;
import processing.core.PApplet;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class CriptoSquareApplication extends PApplet {

    private Drawer drawer;

    @Override
    public void settings() {
        size(1270, 1270);
    }

    @Override
    public void setup() {
        noStroke();
        fill(0);
        background(255);

        drawer = new StickFigureDrawer(this);
    }

    @Override
    public void draw() {
        placeShapesInSquareGridOrderedByNumberOfSticks(2, 10);
        noLoop();
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
            result.add(String.format("%12s", Integer.toBinaryString(i)).replace(' ', '0'));

        return result;
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
}
