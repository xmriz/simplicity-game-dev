package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public abstract class Entity {

    GamePanel gamePanel;

    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNumber = 1;

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    public int actionLockCounter = 0;

    public Entity(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void setAction(){

    }

    public void update(){
        setAction();

        collisionOn = false;
        gamePanel.collisionChecker.checkTile(this);
        gamePanel.collisionChecker.checkSim(this);

        if (!collisionOn) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }

        spriteCounter++;
        if (spriteCounter > 12) {
            spriteCounter = 0;
            if (spriteNumber == 1) {
                spriteNumber = 2;
            } else {
                spriteNumber = 1;
            }
        }
    }


    public void draw(Graphics2D g2d){
        BufferedImage image = null;
        int screenX = worldX - gamePanel.sim.worldX + gamePanel.sim.screenX; // position of the tile in the screen
        int screenY = worldY - gamePanel.sim.worldY + gamePanel.sim.screenY;

        if (worldX - gamePanel.tileSize < gamePanel.sim.worldX + gamePanel.sim.screenX && worldX + gamePanel.tileSize > gamePanel.sim.worldX - gamePanel.sim.screenX
            && worldY - gamePanel.tileSize < gamePanel.sim.worldY + gamePanel.sim.screenY && worldY + gamePanel.tileSize > gamePanel.sim.worldY - gamePanel.sim.screenY){

                switch (direction) {
                    case "up":
                        if (spriteNumber == 1) {
                            image = up1;
                        }
                        if (spriteNumber == 2) {
                            image = up2;
                        }
                        break;
                    case "down":
                        if (spriteNumber == 1) {
                            image = down1;
                        }
                        if (spriteNumber == 2) {
                            image = down2;
                        }
                        break;
                    case "left":
                        if (spriteNumber == 1) {
                            image = left1;
                        }
                        if (spriteNumber == 2) {
                            image = left2;
                        }
                        break;
                    case "right":
                        if (spriteNumber == 1) {
                            image = right1;
                        }
                        if (spriteNumber == 2) {
                            image = right2;
                        }
                        break;
                }

                g2d.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }

    public BufferedImage setupImage(String imagePath){
        UtilityTool uTool = new UtilityTool();
        BufferedImage scaledImage = null;

        try {
            scaledImage = ImageIO.read(new FileInputStream(new File("assets/" + imagePath +".png")));
            scaledImage = uTool.scaleImage(scaledImage, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scaledImage;
    }
}
