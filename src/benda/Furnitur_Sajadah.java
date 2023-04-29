package benda;

import java.awt.Rectangle;

import main.GamePanel;

public class Furnitur_Sajadah extends Furnitur{
    public Furnitur_Sajadah(GamePanel gamePanel){
        name = "Sajadah";
        image = setupImage("benda/furnitur/sajadah");
        collision = false;
        dimensiX = 2;
        dimensiY = 1;
        harga = 10;
        aksi = "Salat";
        solidArea = new Rectangle(0, 0, 48*dimensiX, 48*dimensiY);
        this.gamePanel = gamePanel;
    }

    @Override
    public void action(){
        // aksi masak
        System.out.println("Aksi Salat");
        gamePanel.gameState = gamePanel.inputDurasiSalatState;
    }

}