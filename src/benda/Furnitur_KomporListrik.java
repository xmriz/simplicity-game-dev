package benda;

import java.awt.Rectangle;

import main.GamePanel;

public class Furnitur_KomporListrik extends Furnitur{
    public Furnitur_KomporListrik(GamePanel gamePanel){
        setName("Kompor Listrik");
        setImage(setupImage("benda/furnitur/kompor_listrik"));
        setCollision(false);
        setDimensiX(1);
        setDimensiY(1);
        setHarga(200);
        setAksi("Memasak");
        setSolidArea(new Rectangle(0, 0, 48*getDimensiX(), 48*getDimensiY()));
        this.gamePanel = gamePanel;
    }

    @Override
    public void action(){
        // aksi masak
        System.out.println("Aksi Masak");
        gamePanel.gameState = gamePanel.resepState;
    }
}