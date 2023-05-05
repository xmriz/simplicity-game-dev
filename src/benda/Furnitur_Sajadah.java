package benda;

import java.awt.Rectangle;

import main.GamePanel;

public class Furnitur_Sajadah extends Furnitur{
    public Furnitur_Sajadah(GamePanel gamePanel){
        setName("Sajadah");
        setImage(setupImage("benda/furnitur/sajadah"));
        setCollision(false);
        setDimensiX(2);
        setDimensiY(1);
        setHarga(10);
       setAksi("Shalat");
        setSolidArea(new Rectangle(0, 0, 48*getDimensiX(), 48*getDimensiY()));
        this.gamePanel = gamePanel;
    }

    @Override
    public void action(){
        // aksi masak
        System.out.println("Aksi Shalat");
        gamePanel.setGameState(gamePanel.getInputDurasiShalatState());
    }

}