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
        int screenX = worldX - gamePanel.listSim.get(gamePanel.indexCurrentSim).worldX + gamePanel.listSim.get(gamePanel.indexCurrentSim).screenX; // position of the tile in the screen
        int screenY = worldY - gamePanel.listSim.get(gamePanel.indexCurrentSim).worldY + gamePanel.listSim.get(gamePanel.indexCurrentSim).screenY;

        if (worldX - gamePanel.tileSize < gamePanel.listSim.get(gamePanel.indexCurrentSim).worldX + gamePanel.listSim.get(gamePanel.indexCurrentSim).screenX
                && worldX + gamePanel.tileSize > gamePanel.listSim.get(gamePanel.indexCurrentSim).worldX - gamePanel.listSim.get(gamePanel.indexCurrentSim).screenX
                && worldY - gamePanel.tileSize < gamePanel.listSim.get(gamePanel.indexCurrentSim).worldY + gamePanel.listSim.get(gamePanel.indexCurrentSim).screenY
                && worldY + gamePanel.tileSize > gamePanel.listSim.get(gamePanel.indexCurrentSim).worldY - gamePanel.listSim.get(gamePanel.indexCurrentSim).screenY) {
            g2d.drawImage(image, screenX, screenY, gamePanel.tileSize*dimensiX, gamePanel.tileSize*dimensiY, null);
        }
    }
} 
