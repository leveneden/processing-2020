package fractal_mosaic.model;

import common.Drawable;
import processing.core.PApplet;
import processing.core.PImage;

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
        recursivelyDrawImageMultipleTimesFormingAFrame(processing, image);
        // 2. colocar la imagen en el sentro del lienzo
        processing.image(image.get(), image.getX(), image.getY());
    }

    private void recursivelyDrawImageMultipleTimesFormingAFrame(PApplet processing, PositionedImage previousImage) {
        // 3. redimensionar la imagen original a un cuarto de su tamaño
        PositionedImage image = previousImage.copy();
        resizeToAQuarter(image);
        // 5. si la imagen resultante tiene los lados más grandes que 1
        if (image.getWidth() > 1 && image.getHeight() > 1) {
            // encontrar la nueva posicion de la imagen mas pequeña
            Point imageLocation = new Point(image.getWidth() / 2, image.getHeight() / 2);
            // llamada recursiva
            recursivelyDrawImageMultipleTimesFormingAFrame(processing, image);
            // 4. dibujar marco
            drawFrame(processing, image);

        }
    }

    private void drawFrame(PApplet processing, PositionedImage image) {
        // TODO: implement
    }

    private void resizeToAQuarter(PositionedImage image) {
        int newWidth = 0;
        int newHeight = 0;
        
        // check if you can divide each side uniformly
        if (image.getWidth() % 2 == 0) {
            // if you can, do it
            newWidth = image.getWidth() / 2;
        } else {
            // otherwise, cut it in half + 1
            newWidth = image.getWidth() / 2 + 1;
        }

        if (image.getHeight() % 2 == 0) {
            // if you can, do it
            newHeight = image.getHeight() / 2;
        } else {
            // otherwise, cut it in half + 1
            newHeight = image.getHeight() / 2 + 1;
        }
        
        image.get().resize(newWidth, newHeight);
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
