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

    @Override
    public void draw(Graphics2D g2d, GamePanel gamePanel) {
        int screenX = worldX - gamePanel.sim.worldX + gamePanel.sim.screenX; // position of the tile in the screen
        int screenY = worldY - gamePanel.sim.worldY + gamePanel.sim.screenY;

        if (worldX - gamePanel.tileSize < gamePanel.sim.worldX + gamePanel.sim.screenX
                && worldX + gamePanel.tileSize > gamePanel.sim.worldX - gamePanel.sim.screenX
                && worldY - gamePanel.tileSize < gamePanel.sim.worldY + gamePanel.sim.screenY
                && worldY + gamePanel.tileSize > gamePanel.sim.worldY - gamePanel.sim.screenY) {
            g2d.drawImage(image, screenX, screenY, gamePanel.tileSize*dimensiX, gamePanel.tileSize*dimensiY, null);
        }
    }
} 
