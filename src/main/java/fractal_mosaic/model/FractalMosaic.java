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
        PImage image = processing.loadImage(imageLocation);
        // 1. calcular el tamaño de la imagen a producir
        Point size = getSizeOfEndResult(image.width, image.height);
        Point imageLocation = new Point(
                processing.width / 2 - image.width / 2,
                processing.height / 2 - image.height / 2
        );
        recursivelyDrawImageMultipleTimesFormingAFrame(processing, image, imageLocation);
        // 2. colocar la imagen en el sentro del lienzo
        processing.image(image, imageLocation.x, imageLocation.y);
    }

    private void recursivelyDrawImageMultipleTimesFormingAFrame(PApplet processing, PImage previousImage, Point previousImageLocation) {

        // 3. redimensionar la imagen original a un cuarto de su tamaño
        PImage image = previousImage.copy();
        resizeToAQuarter(image);
        // 5. si la imagen resultante tiene los lados más grandes que 1
        if (image.width > 1 && image.height > 1) {
            // encontrar la nueva posicion de la imagen mas pequeña
            Point imageLocation = new Point(image.width / 2, image.height / 2);
            // llamada recursiva
            recursivelyDrawImageMultipleTimesFormingAFrame(processing, image, imageLocation);
            // 4. dibujar marco
            drawFrame(processing, image, previousImageLocation);
            
        }
    }

    private void drawFrame(PApplet processing, PImage image, Point previousImageLocation) {

    }

    private void resizeToAQuarter(PImage image) {
        int newWidth = 0;
        int newHeight = 0;
        
        // check if you can divide each side uniformly
        if (image.width % 2 == 0) {
            // if you can, do it
            newWidth = image.width / 2;
        } else {
            // otherwise, cut it in half + 1
            newWidth = image.width / 2 + 1;
        }

        if (image.height % 2 == 0) {
            // if you can, do it
            newHeight = image.height / 2;
        } else {
            // otherwise, cut it in half + 1
            newHeight = image.height / 2 + 1;
        }
        
        image.resize(newWidth, newHeight);
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
