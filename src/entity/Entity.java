package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import benda.Benda;
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
    int dialogCounter = 0;
    
    public List<String> dialogs = new ArrayList<String>();

    public List<Benda> inventory = new ArrayList<>();
    public int maxInventorySize = 33;

    public Entity(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void setAction(){

    }

    public void speak(){
        if (gamePanel.ui.currentDialog != null){
            gamePanel.ui.currentDialog = dialogs.get(dialogCounter);
            dialogCounter++;
            if (dialogCounter > dialogs.size()-1){
                dialogCounter = 0;
            }
        } else {
            dialogCounter = 0;
        }

        // ganti direction sim
        switch(gamePanel.listSim.get(gamePanel.indexCurrentSim).direction){
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
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
        int screenX = worldX - gamePanel.listSim.get(gamePanel.indexCurrentSim).worldX + gamePanel.listSim.get(gamePanel.indexCurrentSim).screenX; // position of the tile in the screen
        int screenY = worldY - gamePanel.listSim.get(gamePanel.indexCurrentSim).worldY + gamePanel.listSim.get(gamePanel.indexCurrentSim).screenY;

        if (worldX - gamePanel.tileSize < gamePanel.listSim.get(gamePanel.indexCurrentSim).worldX + gamePanel.listSim.get(gamePanel.indexCurrentSim).screenX && worldX + gamePanel.tileSize > gamePanel.listSim.get(gamePanel.indexCurrentSim).worldX - gamePanel.listSim.get(gamePanel.indexCurrentSim).screenX
            && worldY - gamePanel.tileSize < gamePanel.listSim.get(gamePanel.indexCurrentSim).worldY + gamePanel.listSim.get(gamePanel.indexCurrentSim).screenY && worldY + gamePanel.tileSize > gamePanel.listSim.get(gamePanel.indexCurrentSim).worldY - gamePanel.listSim.get(gamePanel.indexCurrentSim).screenY){

                switch (direction) {
                    case "up":
                        if (spriteNumber == 1) {
                            image = up1;
                        } else {
                            image = up2;
                        }
                        break;
                    case "down":
                        if (spriteNumber == 1) {
                            image = down1;
                        } else {
                            image = down2;
                        }
                        break;
                    case "left":
                        if (spriteNumber == 1) {
                            image = left1;
                        } else {
                            image = left2;
                        }
                        break;
                    case "right":
                        if (spriteNumber == 1) {
                            image = right1;
                        } else {
                            image = right2;
                        }
                        break;
                }

                g2d.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }

    public BufferedImage setupImage(String imagePath){
        BufferedImage scaledImage = null;

        try {
            scaledImage = ImageIO.read(new FileInputStream(new File("assets/" + imagePath +".png")));
            scaledImage = UtilityTool.scaleImage(scaledImage, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scaledImage;
    }
}
