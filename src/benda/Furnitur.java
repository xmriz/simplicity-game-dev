package benda;

import java.awt.Graphics2D;

import main.GamePanel;

public abstract class Furnitur extends Benda{
    public int dimensiX;
    public int dimensiY;
    public int harga;
    public String aksi;
    public Furnitur(){
        category = "Furnitur";
        stackable = true;
    }

    // bakal di override di class turunannya
    public void interact(){

    }

    @Override
    public void draw(Graphics2D g2d, GamePanel gamePanel) {
        int screenX = worldX - gamePanel.currentSim.worldX + gamePanel.currentSim.screenX; // position of the tile in the screen
        int screenY = worldY - gamePanel.currentSim.worldY + gamePanel.currentSim.screenY;

        if (worldX - gamePanel.tileSize < gamePanel.currentSim.worldX + gamePanel.currentSim.screenX
                && worldX + gamePanel.tileSize > gamePanel.currentSim.worldX - gamePanel.currentSim.screenX
                && worldY - gamePanel.tileSize < gamePanel.currentSim.worldY + gamePanel.currentSim.screenY
                && worldY + gamePanel.tileSize > gamePanel.currentSim.worldY - gamePanel.currentSim.screenY) {
            g2d.drawImage(image, screenX, screenY, gamePanel.tileSize*dimensiX, gamePanel.tileSize*dimensiY, null);
        }
    }
} 
