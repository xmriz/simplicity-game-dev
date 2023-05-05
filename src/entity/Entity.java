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

    private int worldX, worldY;
    private int speed;

    private BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    private String direction;

    private int spriteCounter = 0;
    private int spriteNumber = 1;

    private Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    private int solidAreaDefaultX, solidAreaDefaultY;
    private boolean collisionOn = false;

    private int actionLockCounter = 0;
    int dialogCounter = 0;

    private List<String> dialogs = new ArrayList<String>();

    private List<Benda> inventory = new ArrayList<>();
    private int maxInventorySize = 33;

    public int getMaxInventorySize() {
        return maxInventorySize;
    }

    public void setMaxInventorySize(int maxInventorySize) {
        this.maxInventorySize = maxInventorySize;
    }

    public List<Benda> getInventory() {
        return inventory;
    }

    public void setInventory(List<Benda> inventory) {
        this.inventory = inventory;
    }

    public List<String> getDialogs() {
        return dialogs;
    }

    public void setDialogs(List<String> dialogs) {
        this.dialogs = dialogs;
    }

    public int getActionLoctCounter() {
        return actionLockCounter;
    }

    public void incActionLoctCounter(int num) {
        this.actionLockCounter += num;
    }

    public void setActionLoctCounter(int actionLockCounter) {
        this.actionLockCounter = actionLockCounter;
    }

    public boolean getCollisionOn() {
        return collisionOn;
    }

    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    public void setSolidAreaDefaultX(int solidAreaDefaultX) {
        this.solidAreaDefaultX = solidAreaDefaultX;
    }

    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

    public void setSolidAreaDefaultY(int solidAreaDefaultY) {
        this.solidAreaDefaultY = solidAreaDefaultY;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }

    public int getSpriteNumber() {
        return spriteNumber;
    }

    public void setSpriteNumber(int spriteNumber) {
        this.spriteNumber = spriteNumber;
    }

    public int getSpriteCounter() {
        return spriteCounter;
    }

    public void incSpriteCounter(int a) {
        this.spriteCounter += a;
    }

    public void setSpriteCounter(int spriteCounter) {
        this.spriteCounter = spriteCounter;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getWorldX() {
        return worldX;
    }

    public void incWorldX(int a) {
        this.worldX += a;
    }

    public void decWorldX(int a) {
        this.worldX -= a;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public void incWorldY(int a) {
        this.worldY += a;
    }

    public void decWorldY(int a) {
        this.worldY -= a;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public BufferedImage getUp1() {
        return up1;
    }

    public void setUp1(BufferedImage up1) {
        this.up1 = up1;
    }

    public BufferedImage getUp2() {
        return up2;
    }

    public void setUp2(BufferedImage up2) {
        this.up2 = up2;
    }

    public BufferedImage getDown1() {
        return down1;
    }

    public void setDown1(BufferedImage down1) {
        this.down1 = down1;
    }

    public BufferedImage getDown2() {
        return down2;
    }

    public void setDown2(BufferedImage down2) {
        this.down2 = down2;
    }

    public BufferedImage getLeft1() {
        return left1;
    }

    public void setLeft1(BufferedImage left1) {
        this.left1 = left1;
    }

    public BufferedImage getLeft2() {
        return left2;
    }

    public void setLeft2(BufferedImage left2) {
        this.left2 = left2;
    }

    public BufferedImage getRight1() {
        return right1;
    }

    public void setRight1(BufferedImage right1) {
        this.right1 = right1;
    }

    public BufferedImage getRight2() {
        return right2;
    }

    public void setRight2(BufferedImage right2) {
        this.right2 = right2;
    }

    public Entity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setAction() {

    }

    public void speak() {
        if (gamePanel.getUi().getCurrentDialog() != null) {
            gamePanel.getUi().setCurrentDialog(dialogs.get(dialogCounter));
            dialogCounter++;
            if (dialogCounter > dialogs.size() - 1) {
                dialogCounter = 0;
            }
        } else {
            dialogCounter = 0;
        }

        // ganti direction sim
        switch (gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getDirection()) {
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

    public void update() {
        setAction();

        collisionOn = false;
        gamePanel.getCollisionChecker().checkTile(this);
        gamePanel.getCollisionChecker().checkSim(this);

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

    public void draw(Graphics2D g2d) {
        BufferedImage image = null;
        int screenX = worldX - gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getWorldX()
                + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getScreenX(); // position of the tile in
                                                                                           // the screen
        int screenY = worldY - gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getWorldY()
                + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getScreenY();

        if (worldX - gamePanel.getTileSize() < gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getWorldX()
                + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getScreenX()
                && worldX + gamePanel.getTileSize() > gamePanel.getListSim().get(gamePanel.getIndexCurrentSim())
                        .getWorldX()
                        - gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getScreenX()
                && worldY - gamePanel.getTileSize() < gamePanel.getListSim().get(gamePanel.getIndexCurrentSim())
                        .getWorldY()
                        + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getScreenY()
                && worldY + gamePanel.getTileSize() > gamePanel.getListSim().get(gamePanel.getIndexCurrentSim())
                        .getWorldY()
                        - gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getScreenY()) {

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

            g2d.drawImage(image, screenX, screenY, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
        }
    }

    public BufferedImage setupImage(String imagePath) {
        BufferedImage scaledImage = null;

        try {
            scaledImage = ImageIO.read(new FileInputStream(new File("assets/" + imagePath + ".png")));
            scaledImage = UtilityTool.scaleImage(scaledImage, gamePanel.getTileSize(), gamePanel.getTileSize());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scaledImage;
    }
}
