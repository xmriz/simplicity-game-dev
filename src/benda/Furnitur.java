package benda;

import java.awt.Graphics2D;

import main.GamePanel;

public abstract class Furnitur extends Benda{
    private int dimensiX;
    private int dimensiY;
    private int harga;
    private String aksi;

    public int getDimensiX(){
        return dimensiX;
    }

    public void setDimensiX(int dimensiX){
        this.dimensiX=dimensiX;
    }

    public int getDimensiY(){
        return dimensiY;
    }

    public void setDimensiY(int dimensiY){
        this.dimensiY=dimensiY;
    }

    public int getHarga(){
        return harga;
    }

    public void setHarga(int harga){
        this.harga=harga;
    }

    public String getAksi(){
        return aksi;
    }

    public void setAksi(String aksi){
        this.aksi=aksi;
    }

    public Furnitur(){
        setCategory( "Furnitur");
        setStackable(true);
    }

    // bakal di override di class turunannya
    public void action(){

    }

    @Override
 public void draw(Graphics2D g2d, GamePanel gamePanel) {
        int screenX = getWorldX() - gamePanel.listSim.get(gamePanel.indexCurrentSim).worldX + gamePanel.listSim.get(gamePanel.indexCurrentSim).screenX; // position of the tile in the screen
        int screenY = getWorldY() - gamePanel.listSim.get(gamePanel.indexCurrentSim).worldY + gamePanel.listSim.get(gamePanel.indexCurrentSim).screenY;

        if (getWorldX() - gamePanel.tileSize < gamePanel.listSim.get(gamePanel.indexCurrentSim).worldX + gamePanel.listSim.get(gamePanel.indexCurrentSim).screenX
                && getWorldX() + gamePanel.tileSize > gamePanel.listSim.get(gamePanel.indexCurrentSim).worldX - gamePanel.listSim.get(gamePanel.indexCurrentSim).screenX
                && getWorldY() - gamePanel.tileSize < gamePanel.listSim.get(gamePanel.indexCurrentSim).worldY + gamePanel.listSim.get(gamePanel.indexCurrentSim).screenY
                && getWorldY() + gamePanel.tileSize > gamePanel.listSim.get(gamePanel.indexCurrentSim).worldY - gamePanel.listSim.get(gamePanel.indexCurrentSim).screenY) {
            g2d.drawImage(getImage(), screenX, screenY, gamePanel.tileSize*dimensiX, gamePanel.tileSize*dimensiY, null);
        }
    }
} 
