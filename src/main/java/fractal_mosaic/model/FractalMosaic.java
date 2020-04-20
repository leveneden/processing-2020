package fractal_mosaic.model;

import common.Drawable;
import processing.core.PApplet;

import java.awt.*;

public class FractalMosaic implements Drawable {

    private String imageLocation;

    public FractalMosaic(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    @Override
    public void draw(PApplet processing) {
        // cargar imagen
        PositionedImage image = new PositionedImage(processing.loadImage(imageLocation));

        image.setX(processing.width / 2 - image.getWidth() / 2);
        image.setY(processing.height / 2 - image.getHeight() / 2);

        // 1. calcular el tamaño de la imagen a producir
        Point size = getSizeOfEndResult(image.getWidth(), image.getHeight());
        System.out.println(String.format("Rrequired window size:\nwidth - %d\nheight - %d", size.x, size.y));
        recursivelyDrawImageMultipleTimesFormingAFrame(processing, image);
        // 2. colocar la imagen en el sentro del lienzo
        // processing.image(image.get(), image.getX(), image.getY());
    }

    private void recursivelyDrawImageMultipleTimesFormingAFrame(PApplet processing, PositionedImage previousImage) {
        // 3. redimensionar la imagen original a un cuarto de su tamaño
        PositionedImage image = previousImage.copy();
        resizeToAQuarter(image);
        // 5. si la imagen resultante tiene los lados más grandes que 1
        if (image.getWidth() > 1 && image.getHeight() > 1) {
            // encontrar la nueva posicion de la imagen mas pequeña
            Point imageLocation = new Point(image.getWidth() / 2, image.getHeight() / 2); // todo: HMMMM...
            // llamada recursiva
            recursivelyDrawImageMultipleTimesFormingAFrame(processing, image);
            // 4. dibujar marco
            drawFrame(processing, image, previousImage);

        }
    }

    private void drawFrame(PApplet processing, PositionedImage currentImage, PositionedImage previousImage) {
        // TODO: implement
        Point distanceBetweenImages = new Point(
        // dividir el ancho de la PreviousImage entre dos
                previousImage.getWidth() / 2,
        // dividir el ancho de la PreviousImage entre dos
                previousImage.getHeight() / 2
        );
        // calcular el punto de inicio
        Point framePosition = new Point(
                previousImage.getX() - distanceBetweenImages.x,
                previousImage.getY() - distanceBetweenImages.y
        );


        // for (height)
        for (int y = framePosition.y; y < framePosition.y + (previousImage.getHeight() * 2); y += distanceBetweenImages.y) {
        boolean isFirstIteration = y == framePosition.y;
        boolean isLastIteration = y >= framePosition.y + (distanceBetweenImages.y * 3);

            // if first or last
            if (isFirstIteration || isLastIteration) {
                // draw row
                // for (width)
                for (int x = framePosition.x; x < framePosition.y + (previousImage.getWidth() * 2); x += distanceBetweenImages.x) {
                    processing.image(currentImage.get(), x, y);
                }

            // else
            } else {
                // draw only first and last
                processing.image(currentImage.get(), framePosition.x ,y);
                processing.image(currentImage.get(), framePosition.x + (distanceBetweenImages.x * 3), y);
            }
        }

    }

    private void resizeToAQuarter(PositionedImage image) {
        image.get().resize(divideByTwo(image.getWidth()), divideByTwo(image.getHeight()));
    }

    private int divideByTwo(int sideLength) {
        // check if you can divide each side uniformly
        // if you can, do it
        // otherwise, cut it in half + 1
        return sideLength % 2 == 0 ? sideLength / 2: sideLength / 2 + 1;
    }
    
    private Point getSizeOfEndResult(int imageWidth, int imageHeight) {
        int width = imageWidth;
        int height = imageHeight;

        while (imageWidth > 1 && imageHeight > 1) {

            width += (imageWidth / 2) * 2;
            height += (imageHeight / 2) * 2;

            imageWidth = imageWidth / 2;
            imageWidth = imageWidth / 2;
        }

        return new Point(width, height);
    }
}
