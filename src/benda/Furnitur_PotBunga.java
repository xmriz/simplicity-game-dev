package benda;

import java.awt.Rectangle;

import main.GamePanel;

public class Furnitur_PotBunga extends Furnitur{
    public Furnitur_PotBunga(GamePanel gamePanel){
        name = "Pot Bunga";
        image = setupImage("benda/furnitur/pot_bunga");
        collision = false;
        dimensiX = 1;
        dimensiY = 1;
        harga = 15;
        aksi = "Siram Tanaman";
        solidArea = new Rectangle(0, 0, 48*dimensiX, 48*dimensiY);
        this.gamePanel = gamePanel;
    }

    @Override
    public void action(){
        // aksi tidur
        gamePanel.gameState = gamePanel.inputDurasiSiramTanamanState;
    }
}
