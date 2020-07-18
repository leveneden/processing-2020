package fractal_mosaic.model;

import common.stateless.Drawable;
import processing.core.PApplet;

import java.awt.*;

public class FractalMosaic implements Drawable {

    private String fileName;

    public FractalMosaic(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void draw(PApplet processing) {
        // cargar imagen
        PositionedImage image = new PositionedImage(processing.loadImage(fileName));

        image.get().resize(0,4096);

        image.setX(processing.width / 2 - image.getWidth() / 2);
        image.setY(processing.height / 2 - image.getHeight() / 2);

        // 2. colocar la imagen en el centro del lienzo
        processing.image(image.get(), image.getX(), image.getY());

        Point distanceFromCentralImage = new Point();
        recursivelyDrawImageMultipleTimesFormingAFrame(processing, image, image, distanceFromCentralImage);

        // 1. calcular el tamaño de la imagen a producir
        System.out.println(String.format("Rrequired window size:\nwidth - %d\nheight - %d", distanceFromCentralImage.x * 2 + image.getWidth(), distanceFromCentralImage.y * 2 + image.getHeight()));
    }

    private void recursivelyDrawImageMultipleTimesFormingAFrame(PApplet processing, PositionedImage previousImage, PositionedImage originalImage, Point distanceFromCentralImage) {
        // 3. copiar y redimensionar la imagen original a un cuarto de su tamaño
        PositionedImage image = previousImage.copy();
        resizeToAQuarter(image);

        // 5. si la imagen resultante tiene los lados más grandes que 1
        if (image.getWidth() > 1 && image.getHeight() > 1) {

            // asignar la nueva posicion de la imagen mas pequeña
            image.setX(previousImage.getX() - image.getWidth());
            image.setY(previousImage.getY() -image.getHeight());

            // 4. dibujar marco
            drawFrame(processing, image, previousImage, originalImage, distanceFromCentralImage);

            // llamada recursiva
            recursivelyDrawImageMultipleTimesFormingAFrame(processing, image, originalImage, distanceFromCentralImage);

        }
    }

    private void drawFrame(PApplet processing, PositionedImage currentImage, PositionedImage previousImage, PositionedImage originalImage, Point distanceFromCentralImage) {
        // TODO: implement
        Point distanceBetweenImages = new Point(
        // dividir el ancho de la PreviousImage entre dos
                previousImage.getWidth() / 2,
        // dividir el ancho de la PreviousImage entre dos
                previousImage.getHeight() / 2
        );

        // sumar a la distancia desde la imagen central la separación entre imagenes en el marco que se está por dibujar
        distanceFromCentralImage.translate(distanceBetweenImages.x, distanceBetweenImages.y);

        // calcular el punto de inicio
        Point framePosition = new Point(
                previousImage.getX() - distanceBetweenImages.x,
                previousImage.getY() - distanceBetweenImages.y
        );


        // for (height)
        for (int y = framePosition.y; y < framePosition.y + originalImage.getHeight() + (distanceFromCentralImage.y * 2); y += distanceBetweenImages.y) {
        boolean isFirstIteration = y == framePosition.y;
        boolean isLastIteration = y >= framePosition.y + (distanceFromCentralImage.y * 3);

            // if first or last
            if (isFirstIteration || isLastIteration) {
                // draw row
                // for (width)
                for (int x = framePosition.x; x < framePosition.x + originalImage.getWidth() + (distanceFromCentralImage.x * 2); x += distanceBetweenImages.x) {
                    processing.image(currentImage.get(), x, y);
                }

            // else
            } else {
                // draw only first and last
                processing.image(currentImage.get(), framePosition.x ,y);
                processing.image(currentImage.get(), framePosition.x + (distanceFromCentralImage.x * 3), y);
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
