package benda;

import java.awt.Rectangle;

import main.GamePanel;

public class Furnitur_Shower extends Furnitur{
    public Furnitur_Shower(GamePanel gamePanel){
        setName("Shower");
        setImage(setupImage("benda/furnitur/shower"));
        setCollision(false);
        setDimensiX(3);
        setDimensiY(3);
        setHarga(100);
        setAksi("Mandi");
        setSolidArea(new Rectangle(0, 0, 48*getDimensiX(), 48*getDimensiY()));
        this.gamePanel = gamePanel;
    }

    @Override
    public void action(){
        // aksi mandi
        System.out.println("Aksi Mandi");
        gamePanel.gameState = gamePanel.inputDurasiMandiState;
    }
}
