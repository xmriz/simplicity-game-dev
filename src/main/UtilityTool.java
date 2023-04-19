package main;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class UtilityTool {
    public static BufferedImage scaleImage(BufferedImage original, int width, int height) {

        BufferedImage scaledImage = new BufferedImage(width, height, 2); // scale the image to the tile size
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.drawImage(original, 0, 0, width, width, null);
        g2d.dispose();

        return scaledImage;
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int newWidth, int newHeight) {
        // create a new image with the desired size
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());

        // create a transformation matrix that scales the image
        AffineTransform scaleTransform = AffineTransform.getScaleInstance(
                (double) newWidth / originalImage.getWidth(),
                (double) newHeight / originalImage.getHeight());

        // create a graphics object for the new image and draw the scaled image
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawRenderedImage(originalImage, scaleTransform);
        g2d.dispose();

        // return the resized image
        return resizedImage;
    }

}
