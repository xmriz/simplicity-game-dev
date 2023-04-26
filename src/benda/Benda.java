package benda;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.io.*;

import javax.imageio.ImageIO;

import main.GamePanel;

public abstract class Benda {

    public BufferedImage image;
    public String name;
    public String category = "";
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0, solidAreaDefaultY = 0;
    public boolean stackable = false;
    public int quantity = 1;
    GamePanel gamePanel;

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
        int screenX = worldX - gamePanel.currentSim.worldX + gamePanel.currentSim.screenX; // position of the tile in the screen
        int screenY = worldY - gamePanel.currentSim.worldY + gamePanel.currentSim.screenY;

        if (worldX - gamePanel.tileSize < gamePanel.currentSim.worldX + gamePanel.currentSim.screenX
                && worldX + gamePanel.tileSize > gamePanel.currentSim.worldX - gamePanel.currentSim.screenX
                && worldY - gamePanel.tileSize < gamePanel.currentSim.worldY + gamePanel.currentSim.screenY
                && worldY + gamePanel.tileSize > gamePanel.currentSim.worldY - gamePanel.currentSim.screenY) {
            g2d.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }

}
