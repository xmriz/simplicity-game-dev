package benda;

import java.awt.Rectangle;

import main.GamePanel;

public class Furnitur_KasurKingSize extends Furnitur{
    public Furnitur_KasurKingSize(GamePanel gamePanel){
        name = "Kasur King Size";
        image = setupImage("benda/furnitur/kasur_king_size");
        collision = false;
        dimensiX = 5;
        dimensiY = 2;
        harga = 150;
        aksi = "Tidur";
        solidArea = new Rectangle(0, 0, 48*dimensiX, 48*dimensiY);
        this.gamePanel = gamePanel;
    }

    @Override
    public void action(){
        // aksi tidur
        gamePanel.gameState = gamePanel.inputDurasiTidurState;
    }
}
