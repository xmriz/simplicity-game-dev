package benda;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.io.*;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public abstract class Benda {
    GamePanel gamePanel;

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public boolean isPlaced = false;

    public BufferedImage setupImage(String imagePath) {
        // UtilityTool uTool = new UtilityTool();
        BufferedImage scaledImage = null;

        try {
            scaledImage = ImageIO.read(new FileInputStream(new File("assets/" + imagePath + ".png")));
            // scaledImage = uTool.scaleImage(scaledImage, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scaledImage;
    }

    public void draw(Graphics2D g2d, GamePanel gamePanel) {
        int screenX = worldX - gamePanel.sim.worldX + gamePanel.sim.screenX; // position of the tile in the screen
        int screenY = worldY - gamePanel.sim.worldY + gamePanel.sim.screenY;

        if (worldX - gamePanel.tileSize < gamePanel.sim.worldX + gamePanel.sim.screenX
                && worldX + gamePanel.tileSize > gamePanel.sim.worldX - gamePanel.sim.screenX
                && worldY - gamePanel.tileSize < gamePanel.sim.worldY + gamePanel.sim.screenY
                && worldY + gamePanel.tileSize > gamePanel.sim.worldY - gamePanel.sim.screenY) {
            g2d.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }

}
