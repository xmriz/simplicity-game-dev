package benda;

import java.awt.Rectangle;

import main.GamePanel;

public class Furnitur_Shower extends Furnitur{
    public Furnitur_Shower(GamePanel gamePanel){
        name = "Shower";
        image = setupImage("benda/furnitur/shower");
        collision = false;
        dimensiX = 3;
        dimensiY = 3;
        harga = 100;
        aksi = "Mandi";
        solidArea = new Rectangle(0, 0, 48*dimensiX, 48*dimensiY);
        this.gamePanel = gamePanel;
    }

    @Override
    public void action(){
        // aksi mandi
        gamePanel.gameState = gamePanel.inputDurasiMandiState;
    }
}
