package benda;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.io.*;

import javax.imageio.ImageIO;

import main.GamePanel;

public abstract class Benda {

    private BufferedImage image;
    private String name;
    private String category = "";
    private boolean collision = false;
    private int worldX, worldY;
    private Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    private int solidAreaDefaultX = 0, solidAreaDefaultY = 0;
    private boolean stackable = false;
    private int quantity = 1;
    GamePanel gamePanel;
    public boolean isPlaced = false;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean getCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public int getWorldX() {
        return worldX;
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

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }

    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    public void setsolidAreaDefaultX(int solidAreaDefaultX) {
        this.solidAreaDefaultX = solidAreaDefaultX;
    }

    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

    public void setsolidAreaDefaultY(int solidAreaDefaultY) {
        this.solidAreaDefaultY = solidAreaDefaultY;
    }

    public boolean getStackable() {
        return stackable;
    }

    public void setStackable(boolean stackable) {
        this.stackable = stackable;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void incQuantity(int jumlah) {
        this.quantity += jumlah;
    }

    public void decQuantity(int jumlah) {
        this.quantity -= jumlah;
    }

    public BufferedImage setupImage(String imagePath) {
        BufferedImage scaledImage = null;

        try {
            scaledImage = ImageIO.read(new FileInputStream(new File("assets/" + imagePath + ".png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scaledImage;
    }

    public void draw(Graphics2D g2d, GamePanel gamePanel) {
        int screenX = worldX - gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getWorldX()
                + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getScreenX(); // position of the tile in
                                                                                           // the screen
        int screenY = worldY - gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getWorldY()
                + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getScreenY();

        if (worldX - gamePanel.getTileSize() < gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getWorldX()
                + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getScreenX()
                && worldX + gamePanel.getTileSize() > gamePanel.getListSim().get(gamePanel.getIndexCurrentSim())
                        .getWorldX() - gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getScreenX()
                && worldY - gamePanel.getTileSize() < gamePanel.getListSim().get(gamePanel.getIndexCurrentSim())
                        .getWorldY() + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getScreenY()
                && worldY + gamePanel.getTileSize() > gamePanel.getListSim().get(gamePanel.getIndexCurrentSim())
                        .getWorldY() - gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getScreenY()) {
            g2d.drawImage(image, screenX, screenY, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
        }
    }

}