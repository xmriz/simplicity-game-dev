package benda;

import java.awt.Rectangle;

import main.GamePanel;

public class Furnitur_TV extends Furnitur{
    public Furnitur_TV(GamePanel gamePanel){
        name = "TV";
        image = setupImage("benda/furnitur/tv");
        collision = false;
        dimensiX = 2;
        dimensiY = 2;
        harga = 100;
        aksi = "Nonton";
        solidArea = new Rectangle(0, 0, 48*dimensiX, 48*dimensiY);
        this.gamePanel = gamePanel;
    }

    @Override
    public void action(){
        // aksi nonton
        gamePanel.gameState = gamePanel.inputDurasiNontonState;
    }
}
