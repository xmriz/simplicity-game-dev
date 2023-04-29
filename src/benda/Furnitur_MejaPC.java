package benda;

import java.awt.Rectangle;

import main.GamePanel;

public class Furnitur_MejaPC extends Furnitur{
    public Furnitur_MejaPC(GamePanel gamePanel){
        name = "Meja PC";
        image = setupImage("benda/furnitur/meja_pc");
        collision = false;
        dimensiX = 3;
        dimensiY = 3;
        harga = 75;
        aksi = "Main Game";
        solidArea = new Rectangle(0, 0, 48*dimensiX, 48*dimensiY);
        this.gamePanel = gamePanel;
    }

    @Override
    public void action(){
        // aksi tidur
        gamePanel.gameState = gamePanel.inputDurasiMainGameState;
    }
}
