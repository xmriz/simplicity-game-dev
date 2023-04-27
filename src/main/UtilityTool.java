package main;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

    public static String capitalizeFirstLetter(String string) {
        String[] pecah = string.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pecah.length; i++) {
            sb.append(pecah[i].substring(0, 1).toUpperCase());
            sb.append(pecah[i].substring(1).toLowerCase());
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    public static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void printDataFile(String fileName) {
        String path = "data/";
        String file = path + fileName;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

}
