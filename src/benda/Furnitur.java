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
        int screenX = getWorldX() - gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getWorldX() + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getScreenX(); // position of the tile in the screen
        int screenY = getWorldY() - gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getWorldY() + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getScreenY();

        if (getWorldX() - gamePanel.getTileSize() < gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getWorldX() + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getScreenX()
                && getWorldX() + gamePanel.getTileSize() > gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getWorldX() - gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getScreenX()
                && getWorldY() - gamePanel.getTileSize() < gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getWorldY() + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getScreenY()
                && getWorldY() + gamePanel.getTileSize() > gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getWorldY() - gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getScreenY()) {
            g2d.drawImage(getImage(), screenX, screenY, gamePanel.getTileSize()*dimensiX, gamePanel.getTileSize()*dimensiY, null);
        }
    }
} 
